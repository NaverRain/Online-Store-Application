package com.naverrain.core.services.impl;

import com.naverrain.persistence.enteties.User;
import com.naverrain.persistence.enteties.impl.DefaultUser;
import com.naverrain.core.services.UserManagementService;
import com.naverrain.core.mail.MailSender;
import com.naverrain.core.mail.impl.DefaultMailSender;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultUserManagementService implements UserManagementService {

    private final static String RESOURCES_FOLDER = "resources";
    private final static String CURRENT_TASK_STORAGE_FOLDER = "huginstore";
    private final static String USER_CSV_FILE_NAME = "users.csv";


    private final static int USER_ID_INDEX = 0;
    private final static int USER_FIRST_NAME_INDEX = 1;
    private final static int USER_LAST_NAME_INDEX = 2;
    private final static int USER_PASSWORD_INDEX = 3;
    private final static int USER_EMAIL_INDEX = 4;

    private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
    private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
    private static final String NO_ERROR_MESSAGE = "";


    private static DefaultUserManagementService instance;
    private MailSender mailSender;

    {
        mailSender = DefaultMailSender.getInstance();
    }


    private DefaultUserManagementService() {
    }

    @Override
    public String registerUser(User user) {
        if (user == null) {
            return NO_ERROR_MESSAGE;
        }

        String errorMessage = checkUniqueEmail(user.getEmail());
        if (errorMessage != null && !errorMessage.isEmpty()) {
            return errorMessage;
        }

        saveUser(user);
        return NO_ERROR_MESSAGE;
    }

    private String checkUniqueEmail(String email) {
            List<User> users = loadUsers();
            if (email == null || email.isEmpty()) {
                return EMPTY_EMAIL_ERROR_MESSAGE;
            }
            for (User user : users) {
                if (user != null && user.getEmail() != null && user.getEmail().equalsIgnoreCase(email)) {
                    return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
                }
            }
            return NO_ERROR_MESSAGE;
    }

    public static UserManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultUserManagementService();
        }
        return instance;
    }


    @Override
    public List<User> getUsers() {
            List<User> users = loadUsers();
            DefaultUser.setCounter(users.stream().mapToInt(user -> user.getId()).max().getAsInt());
            return users;
    }

    @Override
    public User getUserByEmail(String userEmail) {
            for (User user : loadUsers()) {
                if (user != null && user.getEmail().equalsIgnoreCase(userEmail)) {
                    return user;
                }
            }
            return null;
    }

    private void saveUser(User user) {
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

        private List<User> loadUsers() {
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

        @Override
        public void resetPasswordForUser(User user) {
            mailSender.sendEmail(user.getEmail(), "Please, use this password to login: " + user.getPassword());
        }
}
