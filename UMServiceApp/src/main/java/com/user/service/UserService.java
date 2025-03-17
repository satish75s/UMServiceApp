package com.user.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.dto.RoleDepartmentDTO;
import com.user.dto.UserRequestDTO;
import com.user.dto.UserResponseDTO;
import com.user.entity.Department;
import com.user.entity.Role;
import com.user.entity.User;
import com.user.entity.UserRoleDepartment;
import com.user.entity.UserRoleDepartmentId;
import com.user.repository.DepartmentRepository;
import com.user.repository.RoleRepository;
import com.user.repository.UserRepository;
import com.user.repository.UserRoleDepartmentRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRoleDepartmentRepository userRoleDepartmentRepository;

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        // Create User
        User user = new User();
        user.setUserId(userRequestDTO.getUserId());
        user.setEmail(userRequestDTO.getEmail());
        user.setEmployeeId(userRequestDTO.getEmployeeId());
        user.setMobile(userRequestDTO.getMobile());
        user.setUsername(userRequestDTO.getUsername());

        // Save User
        userRepository.save(user);

        // Add Roles and Departments
        for (RoleDepartmentDTO rd : userRequestDTO.getRolesAndDepartments()) {
            Role role = roleRepository.findById(rd.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found: " + rd.getRoleId()));
            Department department = departmentRepository.findById(rd.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found: " + rd.getDepartmentId()));

            UserRoleDepartment userRoleDepartment = new UserRoleDepartment();
            userRoleDepartment.setId(new UserRoleDepartmentId(user.getUserId(), role.getRoleId(), department.getDepartmentId()));
            userRoleDepartment.setUser(user);
            userRoleDepartment.setRole(role);
            userRoleDepartment.setDepartment(department);

            userRoleDepartmentRepository.save(userRoleDepartment);
        }

        // Convert to Response DTO
        return convertToResponseDTO(user);
    }
    
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        // Fetch paginated users from the repository
        Page<User> userPage = userRepository.findAll(pageable);

        // Convert each User entity to UserResponseDTO
        List<UserResponseDTO> userResponseDTOs = userPage.getContent().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());

        // Return a paginated response
        return new PageImpl<>(userResponseDTOs, pageable, userPage.getTotalElements());
    }

    private UserResponseDTO convertToResponseDTO(User user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setUserId(user.getUserId());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setEmployeeId(user.getEmployeeId());
        responseDTO.setMobile(user.getMobile());
        responseDTO.setUsername(user.getUsername());

        // Handle null rolesAndDepartments
        Set<UserRoleDepartment> rolesAndDepartments = user.getRolesAndDepartments();
        if (rolesAndDepartments == null) {
            rolesAndDepartments = Collections.emptySet();
        }

        List<RoleDepartmentDTO> roleDepartmentDTOs = rolesAndDepartments.stream()
                .map(urd -> {
                    RoleDepartmentDTO rd = new RoleDepartmentDTO();
                    rd.setRoleId(urd.getRole().getRoleId());
                    rd.setRoleName(urd.getRole().getRoleName());
                    rd.setDepartmentId(urd.getDepartment().getDepartmentId());
                    rd.setDepartmentName(urd.getDepartment().getDepartmentName());
                    return rd;
                })
                .collect(Collectors.toList());

        responseDTO.setRolesAndDepartments(roleDepartmentDTOs);
        return responseDTO;
    }
}
