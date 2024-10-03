package com.naverrain.dao.impl;

import com.naverrain.dao.CategoryDao;
import com.naverrain.dao.ProductDao;
import com.naverrain.dto.ProductDto;
import com.naverrain.utis.db.DBUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlJdbcProductDao implements ProductDao {

    private CategoryDao categoryDao;

    {
        categoryDao = new MySqlJdbcCategoryDao();
    }

    @Override
    public List<ProductDto> getProducts() {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM product");
                var res = ps.executeQuery()){
            List<ProductDto> products = new ArrayList<>();

            while (res.next()){
                ProductDto product = new ProductDto();
                product.setId(res.getInt("id"));
                product.setProductName(res.getString("product_name"));
                product.setPrice(res.getBigDecimal("price"));
                product.setCategoryDto(categoryDao.getCategoryByCategoryId(res.getInt("category_id")));
                products.add(product);
            }
            return products;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductDto getProductByProductId(int id) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM product WHERE id = ?")) {
            ps.setInt(1, id);
            try (var res = ps.executeQuery()){
                if (res.next()){
                    ProductDto product = new ProductDto();
                    product.setId(res.getInt("id"));
                    product.setProductName(res.getString("product_name"));
                    product.setPrice(res.getBigDecimal("price"));
                    product.setCategoryDto(categoryDao.getCategoryByCategoryId(res.getInt("category_id")));
                    return product;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
