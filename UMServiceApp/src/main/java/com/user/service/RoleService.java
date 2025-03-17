package com.user.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.Role;
import com.user.repository.RoleRepository;
import com.user.repository.UserRoleDepartmentRepository;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleDepartmentRepository userRoleDepartmentRepository;
    
    public Role createRole(Role role) {
        if (roleRepository.existsByRoleName(role.getRoleName())) {
            throw new RuntimeException("Role with the same name already exists");
        }
        return roleRepository.save(role);
    }
    
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

   /* public void deleteRole(Long roleId) {
        if (!userRoleDepartmentRepository.existsByRoleId(roleId)) {
            roleRepository.deleteById(roleId);
        } else {
            throw new RuntimeException("Role is associated with users and cannot be deleted");
        }
    }*/

    // Other CRUD methods
}