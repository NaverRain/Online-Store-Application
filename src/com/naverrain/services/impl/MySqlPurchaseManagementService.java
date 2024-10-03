package com.naverrain.services.impl;

import com.naverrain.dao.PurchaseDao;
import com.naverrain.dao.impl.MySqlJdbcPurchaseDao;
import com.naverrain.dto.PurchaseDto;
import com.naverrain.dto.converter.PurchaseDtoToPurchaseConverter;
import com.naverrain.enteties.Purchase;
import com.naverrain.services.PurchaseManagementService;

import java.util.List;

public class MySqlPurchaseManagementService implements PurchaseManagementService {

    PurchaseDao purchaseDao;
    PurchaseDtoToPurchaseConverter purchaseConverter;
    {
        purchaseDao = new MySqlJdbcPurchaseDao();
        purchaseConverter = new PurchaseDtoToPurchaseConverter();
    }

    @Override
    public void addPurchase(Purchase purchase) {
        purchaseDao.savePurchase(purchaseConverter.convertPurchaseToPurchaseDto(purchase));
    }

    @Override
    public List<Purchase> getPurchasesByUserId(int userId) {
        List<PurchaseDto> purchaseDtos = purchaseDao.getPurchaseByUserId(userId);
        return purchaseConverter.convertPurchaseDtosToPurchases(purchaseDtos);
    }

    @Override
    public List<Purchase> getPurchases() {
        List<PurchaseDto> purchaseDtos = purchaseDao.getPurchases();
        return purchaseConverter.convertPurchaseDtosToPurchases(purchaseDtos);
    }
}
