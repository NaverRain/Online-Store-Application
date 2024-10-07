package com.naverrain.persistence.dao;

import com.naverrain.persistence.dto.PurchaseDto;

import java.util.List;

public interface PurchaseDao {

    void savePurchase(PurchaseDto purchase);

    List<PurchaseDto> getPurchaseByUserId(int userId);

    List<PurchaseDto> getPurchases();

}
