package com.naverrain.utis.comparator;

import com.naverrain.enteties.Product;

import java.util.Comparator;

public class CustomProductComparator implements Comparator<Product> {

    @Override
    public int compare(Product product1, Product product2){
        int result = product1.getCategoryName().compareTo(product2.getCategoryName());

        if (result == 0) {
            double priceDelta = product1.getPrice() - product2.getPrice();
            result = priceDelta < 0 ? 1 : (priceDelta == 0) ? 0 : -1;
        }

        if (result == 0){
            result = product1.getProductName().compareTo(product2.getProductName());
        }

        return result;
    }

}
