package com.naverrain.services.impl;

import com.naverrain.dao.ProductDao;
import com.naverrain.dto.ProductDto;
import com.naverrain.dto.converter.ProductDtoToProductConverter;
import com.naverrain.enteties.Product;
import com.naverrain.services.ProductManagementService;

import java.util.List;

public class MySqlProductManagementService implements ProductManagementService {

    private ProductDao productDao;
    private ProductDtoToProductConverter productConverter;

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
