package com.naverrain.core.services;

import com.naverrain.persistence.enteties.Product;

import java.util.List;

public interface ProductManagementService {
    List<Product> getProducts();

    Product getProductById(int productId);
}
