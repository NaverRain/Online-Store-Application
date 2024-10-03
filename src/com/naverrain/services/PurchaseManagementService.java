package com.naverrain.services;

import com.naverrain.enteties.Purchase;

import java.util.List;

public interface PurchaseManagementService {

    void addPurchase(Purchase purchase);

    List<Purchase> getPurchasesByUserId(int userId);

    List<Purchase> getPurchases();
}
