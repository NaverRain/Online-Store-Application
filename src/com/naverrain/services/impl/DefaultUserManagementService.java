package com.naverrain.services.impl;

import com.naverrain.enteties.User;
import com.naverrain.enteties.impl.DefaultUser;
import com.naverrain.services.UserManagementService;
import com.naverrain.storage.impl.DefaultUserStoringService;

import java.util.List;

public class DefaultUserManagementService implements com.naverrain.services.UserManagementService {

    private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
    private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
    private static final String NO_ERROR_MESSAGE = "";

    private static DefaultUserManagementService instance;
    private static DefaultUserStoringService defaultUserStoringService;

    static {
        defaultUserStoringService = DefaultUserStoringService.getInstance();
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

        defaultUserStoringService.saveUser(user);
        return NO_ERROR_MESSAGE;
    }

    private String checkUniqueEmail(String email) {
        List<User> users = defaultUserStoringService.loadUsers();
        if (email == null || email.isEmpty()) {
            return EMPTY_EMAIL_ERROR_MESSAGE;
        }
        for (User user : users) {
            if (user != null &&
                    user.getEmail() != null &&
                    user.getEmail().equalsIgnoreCase(email)) {
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
        List<User> users = defaultUserStoringService.loadUsers();
        DefaultUser.setCounter(users.stream()
                .mapToInt(user -> user.getId())
                .max().getAsInt());
        return users;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        for (User user : defaultUserStoringService.loadUsers()) {
            if (user != null && user.getEmail().equalsIgnoreCase(userEmail)) {
                return user;
            }
        }
        return null;
    }


}
