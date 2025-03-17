package com.user.entity;



import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "employee_id", unique = true)
    private String employeeId;

    @Column(nullable = false)
    private String mobile;

    @Column(nullable = false, unique = true)
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UserRoleDepartment> rolesAndDepartments = new HashSet<>();

    // Helper method to add roles and departments
    public void addRoleAndDepartment(Role role, Department department) {
        UserRoleDepartment userRoleDepartment = new UserRoleDepartment();
        userRoleDepartment.setUser(this);
        userRoleDepartment.setRole(role);
        userRoleDepartment.setDepartment(department);
        rolesAndDepartments.add(userRoleDepartment);
    }
}

