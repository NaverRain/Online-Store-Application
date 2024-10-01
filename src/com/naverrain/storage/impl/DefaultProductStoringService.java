package com.naverrain.storage.impl;

import com.naverrain.enteties.impl.DefaultProduct;
import com.naverrain.storage.ProductStoringService;
import com.naverrain.enteties.Product;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultProductStoringService implements ProductStoringService {

    private final static String PRODUCTS_CSV_FILE_NAME = "products.csv";
    private final static String CURRENT_TASK_RESOURCE_FOLDER = "huginstore";
    private final static String RESOURCES_FOLDER = "resources";

    private final static int PRODUCT_ID_INDEX = 0;
    private final static int PRODUCT_NAME_INDEX = 1;
    private final static int PRODUCT_CATEGORY_NAME_INDEX = 2;
    private final static int PRODUCT_PRICE_INDEX = 3;

    @Override
    public List<Product> loadProducts() {
        try (var stream = Files.lines(Paths.get(RESOURCES_FOLDER, CURRENT_TASK_RESOURCE_FOLDER,
                PRODUCTS_CSV_FILE_NAME))) {
            return stream
                    .filter(Objects::nonNull)
                    .filter(line -> !line.isEmpty())
                    .map(line -> {
                        String[] productElements = line.split(",");
                        return new DefaultProduct(Integer.valueOf(productElements[PRODUCT_ID_INDEX]),
                                productElements[PRODUCT_NAME_INDEX],
                                productElements[PRODUCT_CATEGORY_NAME_INDEX],
                                Double.valueOf(productElements[PRODUCT_PRICE_INDEX]));
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }
}
