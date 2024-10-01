package com.naverrain.storage;

import com.naverrain.enteties.Order;

import java.util.List;

public interface OrderStoringService {
    void saveOrders(List<Order> order);

    List<Order> loadOrders();
}
