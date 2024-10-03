package com.naverrain.dto.converter;

import com.naverrain.dto.RoleDto;

public class RoleDtoToRoleConverter {


    public RoleDto convertRoleNameToRoleDtoWithOnlyRoleName(String roleName) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(roleName);
        return roleDto;
    }
}
