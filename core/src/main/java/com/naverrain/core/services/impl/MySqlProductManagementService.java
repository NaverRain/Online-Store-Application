package com.naverrain.core.services.impl;

import com.naverrain.persistence.dao.ProductDao;
import com.naverrain.persistence.dao.impl.MySqlJdbcProductDao;
import com.naverrain.persistence.dto.ProductDto;
import com.naverrain.persistence.dto.converter.ProductDtoToProductConverter;
import com.naverrain.persistence.enteties.Product;
import com.naverrain.core.services.ProductManagementService;

import java.util.List;

public class MySqlProductManagementService implements ProductManagementService {

    private ProductDao productDao;
    private ProductDtoToProductConverter productConverter;

    {
        productDao = new MySqlJdbcProductDao();
        productConverter = new ProductDtoToProductConverter();
    }

    @Override
    public List<Product> getProducts() {
        List<ProductDto> productDtos = productDao.getProducts();
        return productConverter.convertProductDtosToProducts(productDtos);
    }

    @Override
    public Product getProductById(int productId) {
        ProductDto productDto = productDao.getProductByProductId(productId);
        return productConverter.convertProductDtoToProduct(productDto);
    }
}
