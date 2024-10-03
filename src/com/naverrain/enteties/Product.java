package com.naverrain.enteties;

import java.io.Serializable;

public interface Product extends Serializable {

    int getId();

    String getProductName();

    String getCategoryName();

    double getPrice();

    void setId(int id);

    void setProductName(String productName);

    void setCategoryName(String categoryName);

    void setPrice(double price);
}
