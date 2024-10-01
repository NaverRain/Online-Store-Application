package com.naverrain.storage.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.naverrain.enteties.Order;
import com.naverrain.storage.OrderStoringService;



public class DefaultOrderStoringService implements OrderStoringService {

    private static final String ORDERS_DATA_FILE_NAME = "orders.data";
    private static final String CURRENT_TASK_RESOURCE_FOLDER = "huginstore";
    private static final String RESOURCES_FOLDER = "resources";

    private static DefaultOrderStoringService instance;

    private DefaultOrderStoringService() {
    }

    @Override
    public void saveOrders(List<Order> orders) {
        try (var oos = new ObjectOutputStream(new FileOutputStream(
                RESOURCES_FOLDER + File.separator + CURRENT_TASK_RESOURCE_FOLDER
                        + File.separator + ORDERS_DATA_FILE_NAME
                ))) {
            oos.writeObject(orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> loadOrders() {
        File file = new File(RESOURCES_FOLDER + File.separator + CURRENT_TASK_RESOURCE_FOLDER
                + File.separator + ORDERS_DATA_FILE_NAME);

        if (!file.exists() || file.length() == 0) {
            System.out.println("No previous orders found. Starting fresh.");
            return new ArrayList<>();
        }

        try (var ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Order>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static OrderStoringService getInstance() {
        if (instance == null) {
            instance = new DefaultOrderStoringService();
        }
        return instance;
    }
}
