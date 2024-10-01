package com.naverrain.services;

import com.naverrain.enteties.Product;

import java.util.List;

public interface ProductManagementService {
    List<Product> getProducts();

    Product getProductById(int productIdToAddToCart);
}
