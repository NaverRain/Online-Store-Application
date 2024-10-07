package com.naverrain.core.services.impl;

import com.naverrain.persistence.dao.UserDao;
import com.naverrain.persistence.dao.impl.MySqlJdbcUserDao;
import com.naverrain.persistence.dto.UserDto;
import com.naverrain.persistence.dto.converter.UserDtoToUserConverter;
import com.naverrain.persistence.enteties.User;
import com.naverrain.core.services.UserManagementService;
import com.naverrain.core.mail.MailSender;
import com.naverrain.core.mail.impl.DefaultMailSender;

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
