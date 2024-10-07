package com.naverrain.core.services.impl;

import com.naverrain.persistence.enteties.Purchase;
import com.naverrain.core.services.PurchaseManagementService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultPurchaseManagementService implements PurchaseManagementService {

    private static final String ORDERS_DATA_FILE_NAME = "orders.data";
    private static final String CURRENT_TASK_RESOURCE_FOLDER = "huginstore";
    private static final String RESOURCES_FOLDER = "resources";

    private static DefaultPurchaseManagementService instance;
    private List<Purchase> purchases;

    {
        purchases = loadPurchases();
        if (purchases == null) {
            purchases = new ArrayList<>();
        }
    }

    public static PurchaseManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultPurchaseManagementService();
        }
        return instance;
    }

    @Override
    public void addPurchase(Purchase purchase) {
        if (purchase == null) {
            return;
        }
        purchases.add(purchase);
        savePurchases(purchases);
    }

    @Override
    public List<Purchase> getPurchasesByUserId(int userId) {
        return getPurchases().stream()
                .filter(Objects::nonNull)
                .filter(order -> order.getCustomerId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Purchase> getPurchases() {
        if (purchases == null || purchases.size() == 0) {
            purchases = loadPurchases();
        }
        return this.purchases;
    }

    void clearServiceState() {
        purchases.clear();
    }

    private void savePurchases(List<Purchase> orders) {
        try (var oos = new ObjectOutputStream(new FileOutputStream(
                RESOURCES_FOLDER + File.separator + CURRENT_TASK_RESOURCE_FOLDER
                        + File.separator + ORDERS_DATA_FILE_NAME
        ))) {
            oos.writeObject(orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Purchase> loadPurchases() {
        File file = new File(RESOURCES_FOLDER + File.separator + CURRENT_TASK_RESOURCE_FOLDER
                + File.separator + ORDERS_DATA_FILE_NAME);

        if (!file.exists() || file.length() == 0) {
            System.out.println("No previous orders found. Starting fresh.");
            return new ArrayList<>();
        }

        try (var ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Purchase>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
