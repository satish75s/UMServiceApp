package com.user.dto;

import com.user.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Long roleId;
    private String roleName;

    public RoleDTO(Role role) {
        this.roleId = role.getRoleId();
        this.roleName = role.getRoleName();
    }
}
