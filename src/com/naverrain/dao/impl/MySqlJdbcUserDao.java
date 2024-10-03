package com.naverrain.dao.impl;

import com.naverrain.dao.RoleDao;
import com.naverrain.dao.UserDao;
import com.naverrain.dto.UserDto;
import com.naverrain.utis.db.DBUtils;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class MySqlJdbcUserDao implements UserDao {

    private RoleDao roleDao;

    {
        roleDao = new MySqlJdbcRoleDao();
    }

    @Override
    public UserDto getUserById(int id) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM user WHERE id = ?")){
            ps.setInt(1, id);
            try (var res = ps.executeQuery()){
                if (res.next()){
                    UserDto user = new UserDto();

                    user.setId(res.getInt("id"));
                    user.setFirstName(res.getString("first_name"));
                    user.setLastName(res.getString("last_name"));
                    user.setEmail(res.getString("email"));
                    user.setRoleDto(roleDao.getRoleById(res.getInt("fk_user_role")));
                    user.setMoney(res.getBigDecimal("money"));
                    user.setCreditCard(res.getString("credit_card"));
                    user.setPassword(res.getString("password"));

                    return user;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM user WHERE email = ?")){
            ps.setString(1, email);
            try (var res = ps.executeQuery()){
                if (res.next()){
                    UserDto user = new UserDto();

                    user.setId(res.getInt("id"));
                    user.setFirstName(res.getString("first_name"));
                    user.setLastName(res.getString("last_name"));
                    user.setEmail(res.getString("email"));
                    user.setRoleDto(roleDao.getRoleById(res.getInt("fk_user_role")));
                    user.setMoney(res.getBigDecimal("money"));
                    user.setCreditCard(res.getString("credit_card"));
                    user.setPassword(res.getString("password"));

                    return user;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserDto> getUsers() {
        try (var connection = DBUtils.getConnection();
             var ps = connection.prepareStatement("SELECT * FROM user");
             var res = ps.executeQuery()){
            List<UserDto> users = new ArrayList<>();
            if (res.next()){
                UserDto user = new UserDto();

                user.setId(res.getInt("id"));
                user.setFirstName(res.getString("first_name"));
                user.setLastName(res.getString("last_name"));
                user.setEmail(res.getString("email"));
                user.setRoleDto(roleDao.getRoleById(res.getInt("fk_user_role")));
                user.setMoney(res.getBigDecimal("money"));
                user.setCreditCard(res.getString("credit_card"));
                user.setPassword(res.getString("password"));

                users.add(user);
            }
            return users;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveUser(UserDto user) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement(
                        "INSERT INTO user (first_name, last_name, email, fk_user_role, money, credit_card, password)" +
                                "VALUE (?, ?, ?, ?, ?, ?, ?);")){
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getEmail());
                if (user.getRoleDto() != null || user.getRoleDto().getId() != null){
                    ps.setInt(4, user.getRoleDto().getId());
                }
                else{
                    ps.setNull(4, Types.NULL);
                }
                ps.setBigDecimal(5, user.getMoney());
                ps.setString(6, user.getCreditCard());
                ps.setString(7, user.getPassword());

                ps.executeQuery();
                return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
