package com.naverrain.persistence.dto.converter;

import com.naverrain.persistence.dto.PurchaseDto;
import com.naverrain.persistence.enteties.Purchase;
import com.naverrain.persistence.enteties.impl.DefaultPurchase;

import java.util.ArrayList;
import java.util.List;

public class PurchaseDtoToPurchaseConverter {

    private ProductDtoToProductConverter productConverter;
    private UserDtoToUserConverter userConverter;
    {
        productConverter = new ProductDtoToProductConverter();
        userConverter = new UserDtoToUserConverter();
    }

    public Purchase convertPurchaseDtoToPurchase(PurchaseDto purchaseDto) {
        Purchase purchase = new DefaultPurchase();

        purchase.setCustomerId(purchaseDto.getUserDto().getId());
        purchase.setCreditCardNumber(purchaseDto.getUserDto().getCreditCard());
        purchase.setProducts(productConverter.convertProductDtosToProducts(purchaseDto.getProductDtos()));

        return purchase;
    }

    public PurchaseDto convertPurchaseToPurchaseDto(Purchase purchase) {
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setProductDtos(productConverter.convertProductsToProductDtos(purchase.getProducts()));
        purchaseDto.setUserDto(userConverter.convertUserIdToUserDtoWithOnlyId(purchase.getCustomerId()));

        return purchaseDto;
    }

    public List<Purchase> convertPurchaseDtosToPurchases(List<PurchaseDto> purchasesDtos) {
        List<Purchase> purchases = new ArrayList<>();
        if (purchasesDtos != null){
            for (PurchaseDto purchaseDto : purchasesDtos){
                purchases.add(convertPurchaseDtoToPurchase(purchaseDto));
            }
        }
        return purchases;
    }

}
