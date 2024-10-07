package com.naverrain.persistence.dao;

import com.naverrain.persistence.dto.ProductDto;

import java.util.List;

public interface ProductDao {

    List<ProductDto> getProducts();

    ProductDto getProductByProductId(int id);
}
