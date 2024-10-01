package com.naverrain.storage;

import com.naverrain.enteties.Product;

import java.util.List;

public interface ProductStoringService {


    List<Product> loadProducts();
}
