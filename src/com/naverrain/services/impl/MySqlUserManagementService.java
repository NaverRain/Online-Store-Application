package com.naverrain.services.impl;

import com.naverrain.dao.UserDao;
import com.naverrain.dao.impl.MySqlJdbcUserDao;
import com.naverrain.dto.UserDto;
import com.naverrain.dto.converter.UserDtoToUserConverter;
import com.naverrain.enteties.User;
import com.naverrain.services.UserManagementService;
import com.naverrain.utis.mail.MailSender;
import com.naverrain.utis.mail.impl.DefaultMailSender;

import java.util.List;

public class MySqlUserManagementService implements UserManagementService {

    private static final String SUCCESSFULL_REGISTRATION_MESSAGE = "User is registered!";
    private static final String REGISTRATION_FAIL_MESSAGE = "The email is already used.";

    private UserDao userDao;
    private UserDtoToUserConverter userConverter;

    private MailSender mailSender;

    {
        userDao = new MySqlJdbcUserDao();
        userConverter = new UserDtoToUserConverter();
        mailSender = DefaultMailSender.getInstance();
    }


    @Override
    public String registerUser(User user) {
        boolean isCreated = userDao.saveUser(userConverter.convertUserToUserDto(user));
        if (isCreated){
            return SUCCESSFULL_REGISTRATION_MESSAGE;
        }
        else {
            return REGISTRATION_FAIL_MESSAGE;
        }
    }

    @Override
    public List<User> getUsers() {
        List<UserDto> userDtos = userDao.getUsers();
        return userConverter.convertUserDtosToUsers(userDtos);
    }

    @Override
    public User getUserByEmail(String userEmail) {
        UserDto userDto = userDao.getUserByEmail(userEmail);
        return userConverter.convertUserDtoToUser(userDto);
    }

    @Override
    public void resetPasswordForUser(User user) {
        mailSender.sendEmail(user.getEmail(), "Here is your password to login: " + user.getPassword());
    }
}
