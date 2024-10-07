package com.naverrain.persistence.dto.converter;

import com.naverrain.persistence.dto.UserDto;
import com.naverrain.persistence.enteties.User;
import com.naverrain.persistence.enteties.impl.DefaultUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserDtoToUserConverter {

    private RoleDtoToRoleConverter roleConverter;
    {
        roleConverter = new RoleDtoToRoleConverter();
    }

    public UserDto convertUserIdToUserDtoWithOnlyId(int customerId) {
        UserDto userDto = new UserDto();
        userDto.setId(customerId);
        return userDto;
    }

    public User convertUserDtoToUser(UserDto userDto) {
        if (userDto == null)
            return null;

        User user = new DefaultUser();

        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        if (userDto.getRoleDto() != null){
            user.setRoleName(userDto.getRoleDto().getRoleName());
        }

        user.setMoney(userDto.getMoney().doubleValue());
        user.setCreditCard(userDto.getCreditCard());

        return user;
    }

    public UserDto convertUserToUserDto(User user) {
        if (user == null)
            return null;

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());

        userDto.setRoleDto(roleConverter
                .convertRoleNameToRoleDtoWithOnlyRoleName(user.getRoleName()));

        userDto.setMoney(BigDecimal.valueOf(user.getMoney()));
        userDto.setCreditCard(user.getCreditCard());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

    public List<User> convertUserDtosToUsers(List<UserDto> userDtos) {
        if (userDtos == null)
            return null;

        List<User> users = new ArrayList<>();

        for (UserDto userDto : userDtos){
            users.add(convertUserDtoToUser(userDto));
        }

        return users;
    }
}
