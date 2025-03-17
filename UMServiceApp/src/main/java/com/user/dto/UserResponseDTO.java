package com.user.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserResponseDTO {
    private String userId;
    private String email;
    private String employeeId;
    private String mobile;
    private String username;
    private List<RoleDepartmentDTO> rolesAndDepartments;
}