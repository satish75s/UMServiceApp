package com.user.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.user.entity.Department;
import com.user.entity.Role;
import com.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String userId;
    private String email;
    private String employeeId;
    private String mobile;
    private String username;
    private List<RoleDTO> roles;         // List of RoleDTO
    private List<DepartmentDTO> departments; // List of DepartmentDTO
    
    
}
