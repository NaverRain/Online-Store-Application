package com.naverrain.dao.impl;

import com.naverrain.dao.ProductDao;
import com.naverrain.dao.PurchaseDao;
import com.naverrain.dao.UserDao;
import com.naverrain.dto.ProductDto;
import com.naverrain.dto.PurchaseDto;
import com.naverrain.utis.db.DBUtils;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlJdbcPurchaseDao implements PurchaseDao {

    ProductDao productDao;
    UserDao userDao;
    {
        userDao = new MySqlJdbcUserDao();
        productDao = new MySqlJdbcProductDao();
    }

    @Override
    public List<PurchaseDto> getPurchaseByUserId(int userId) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM purchase WHERE id = ?")){
            ps.setInt(1, userId);
            List<PurchaseDto> purchases = new ArrayList<>();
            try (var res = ps.executeQuery()){
                while (res.next()){
                    PurchaseDto purchase = new PurchaseDto();
                    purchase.setId(res.getInt("id"));
                    purchase.setUserDto(userDao.getUserById(res.getInt("fk_purchase_user")));
                    List<ProductDto> products = new ArrayList<>();
                    try (var psProducts = connection.prepareStatement("SELECT * FROM purchase_product WHERE purchase_id = " + purchase.getId());
                            var resProducts = psProducts.executeQuery()){
                        while (resProducts.next()){
                            products.add(productDao.getProductByProductId(resProducts.getInt("product_id")));
                        }
                    }
                    purchase.setProductDtos(products);
                    purchases.add(purchase);
                }
            }
            return purchases;
        }
        catch (SQLException e){
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public List<PurchaseDto> getPurchases() {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM purchase");){
            List<PurchaseDto> purchases = new ArrayList<>();

            try (var res = ps.executeQuery()){
                while (res.next()){
                    PurchaseDto purchase = new PurchaseDto();
                    purchase.setId(res.getInt("id"));
                    purchase.setUserDto(userDao.getUserById(res.getInt("fk_purchase_user")));

                    List<ProductDto> products = new ArrayList<>();
                    try (var psProduct = connection.prepareStatement("SELECT * FROM purchase_product WHERE purchase_id = " + purchase.getId());
                         var resProduct = psProduct.executeQuery();){
                        while (resProduct.next()){
                            products.add(productDao.getProductByProductId(res.getInt("product_id")));
                        }
                    }
                    purchase.setProductDtos(products);

                    purchases.add(purchase);
                }
            }
            return purchases;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void savePurchase(PurchaseDto purchase) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("INSERT INTO purchase (fk_purchase_user) VALUES (?);", Statement.RETURN_GENERATED_KEYS);
                var psPurchaseProduct = connection.prepareStatement("INSERT INTO purchase_product (purchase_id, product_id) VALUES (?, ?);")){
            ps.setInt(1, purchase.getUserDto().getId());
            ps.executeUpdate();

            try (var generatedKeys = ps.getGeneratedKeys()){
                if (generatedKeys.next()){

                    for (ProductDto product : purchase.getProductDtos()){
                        psPurchaseProduct.setLong(1, generatedKeys.getLong(1));
                        psPurchaseProduct.setInt(2, product.getId());
                        psPurchaseProduct.addBatch();
                    }

                    psPurchaseProduct.executeBatch();
                }
                else{
                    throw new SQLException("Purchase creating failed. Have no obtained ID");
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
