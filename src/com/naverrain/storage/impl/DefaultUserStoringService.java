package com.naverrain.storage.impl;

import com.naverrain.storage.UserStoringService;
import com.naverrain.enteties.User;
import com.naverrain.enteties.impl.DefaultUser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultUserStoringService implements UserStoringService {

    private final static String RESOURCES_FOLDER = "resources";
    private final static String CURRENT_TASK_STORAGE_FOLDER = "huginstore";
    private final static String USER_CSV_FILE_NAME = "users.csv";


    private final static int USER_ID_INDEX = 0;
    private final static int USER_FIRST_NAME_INDEX = 1;
    private final static int USER_LAST_NAME_INDEX = 2;
    private final static int USER_PASSWORD_INDEX = 3;
    private final static int USER_EMAIL_INDEX = 4;

    private static DefaultUserStoringService instance;

    @Override
    public void saveUser(User user) {
        try {
            Files.writeString(Paths.get(RESOURCES_FOLDER, CURRENT_TASK_STORAGE_FOLDER, USER_CSV_FILE_NAME),
                    System.lineSeparator() + convertToStorableString(user),
                    StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertToStorableString(User user) {
        return user.getId() + "," + user.getFirstName() + "," + user.getLastName() + "," + user.getPassword() + ","
                + user.getEmail();
    }

    @Override
    public List<User> loadUsers() {
        try (var stream = Files
                .lines(Paths.get(RESOURCES_FOLDER, CURRENT_TASK_STORAGE_FOLDER, USER_CSV_FILE_NAME))) {
            return stream.filter(Objects::nonNull)
                    .filter(line -> !line.isEmpty())
                    .map(line -> {
                        String[] userElements = line.split(",");
                        return new DefaultUser(Integer.valueOf(userElements[USER_ID_INDEX]),
                                userElements[USER_FIRST_NAME_INDEX], userElements[USER_LAST_NAME_INDEX],
                                userElements[USER_PASSWORD_INDEX], userElements[USER_EMAIL_INDEX]);
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static DefaultUserStoringService getInstance() {
        if (instance == null) {
            instance = new DefaultUserStoringService();
        }
        return instance;
    }
}
