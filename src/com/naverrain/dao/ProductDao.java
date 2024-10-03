package com.naverrain.dao;

import com.naverrain.dto.ProductDto;

import java.util.List;

public interface ProductDao {

    List<ProductDto> getProducts();

    ProductDto getProductByProductId(int id);
}
