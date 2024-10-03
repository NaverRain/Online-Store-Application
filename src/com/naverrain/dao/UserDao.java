package com.naverrain.dao;

import com.naverrain.dto.UserDto;

import java.util.List;

public interface UserDao {

    boolean saveUser(UserDto user);

    List<UserDto> getUsers();

    UserDto getUserById(int id);

    UserDto getUserByEmail(String email);
}
