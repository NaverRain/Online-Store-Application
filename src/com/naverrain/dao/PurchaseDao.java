package com.naverrain.dao;

import com.naverrain.dto.PurchaseDto;

import java.util.List;

public interface PurchaseDao {

    void savePurchase(PurchaseDto purchase);

    List<PurchaseDto> getPurchaseByUserId(int userId);

    List<PurchaseDto> getPurchases();

}
