package com.naverrain.persistence.dto.converter;

import com.naverrain.persistence.dto.RoleDto;

public class RoleDtoToRoleConverter {


    public RoleDto convertRoleNameToRoleDtoWithOnlyRoleName(String roleName) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(roleName);
        return roleDto;
    }
}
