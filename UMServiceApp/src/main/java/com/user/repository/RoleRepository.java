package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.user.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByRoleName(String roleName);
    @Query("SELECT COUNT(urd) > 0 FROM UserRoleDepartment urd WHERE urd.role.roleId = :roleId")
    boolean isRoleAssociatedWithUser(@Param("roleId") Long roleId);
}
