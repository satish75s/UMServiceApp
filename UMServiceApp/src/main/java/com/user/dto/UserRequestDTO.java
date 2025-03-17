package com.user.dto;



import java.util.List;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String userId;
    private String email;
    private String employeeId;
    private String mobile;
    private String username;
    private List<RoleDepartmentDTO> rolesAndDepartments;
}