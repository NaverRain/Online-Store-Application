package com.naverrain.persistence.dao.impl;

import com.naverrain.persistence.dao.RoleDao;
import com.naverrain.persistence.dto.RoleDto;
import com.naverrain.persistence.utils.db.DBUtils;

import java.sql.SQLException;

public class MySqlJdbcRoleDao implements RoleDao {

    @Override
    public RoleDto getRoleById(int id) {
        try (var connection = DBUtils.getConnection();
                var ps = connection.prepareStatement("SELECT * FROM role WHERE id = ?")) {
            ps.setInt(1, id);
            try (var res = ps.executeQuery()){
                if (res.next()){
                    RoleDto role = new RoleDto();
                    role.setId(res.getInt("id"));
                    role.setRoleName(res.getString("role_name"));

                    return role;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
