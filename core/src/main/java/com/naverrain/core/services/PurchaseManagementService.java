package com.naverrain.core.services;

import com.naverrain.persistence.enteties.Purchase;

import java.util.List;

public interface PurchaseManagementService {

    void addPurchase(Purchase purchase);

    List<Purchase> getPurchasesByUserId(int userId);

    List<Purchase> getPurchases();
}
