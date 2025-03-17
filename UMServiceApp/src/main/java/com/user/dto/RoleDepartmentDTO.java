package com.user.dto;

import lombok.Data;

@Data
public class RoleDepartmentDTO {
    private Long roleId;
    private String roleName;
    private Long departmentId;
    private String departmentName;
}