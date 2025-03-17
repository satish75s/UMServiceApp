package com.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.user.entity.UserRoleDepartment;
import com.user.entity.UserRoleDepartmentId;



public interface UserRoleDepartmentRepository extends JpaRepository<UserRoleDepartment, UserRoleDepartmentId> {
    @Modifying
    @Query("DELETE FROM UserRoleDepartment urd WHERE urd.user.userId = :userId")
    void deleteByUserId(@Param("userId") String userId);

    @Query("SELECT COUNT(urd) > 0 FROM UserRoleDepartment urd WHERE urd.role.roleId = :roleId")
    boolean isRoleAssociatedWithUser(@Param("roleId") Long roleId);

    @Query("SELECT COUNT(urd) > 0 FROM UserRoleDepartment urd WHERE urd.department.departmentId = :departmentId")
    boolean isDepartmentAssociatedWithUser(@Param("departmentId") Long departmentId);
}
