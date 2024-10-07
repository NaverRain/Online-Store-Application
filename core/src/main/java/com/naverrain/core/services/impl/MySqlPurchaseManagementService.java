package com.naverrain.core.services.impl;

import com.naverrain.persistence.dao.PurchaseDao;
import com.naverrain.persistence.dao.impl.MySqlJdbcPurchaseDao;
import com.naverrain.persistence.dto.PurchaseDto;
import com.naverrain.persistence.dto.converter.PurchaseDtoToPurchaseConverter;
import com.naverrain.persistence.enteties.Purchase;
import com.naverrain.core.services.PurchaseManagementService;

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
