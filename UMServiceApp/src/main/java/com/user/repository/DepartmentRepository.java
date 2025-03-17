package com.user.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.user.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByDepartmentName(String departmentName);
    @Query("SELECT COUNT(urd) > 0 FROM UserRoleDepartment urd WHERE urd.department.departmentId = :departmentId")
    boolean isDepartmentAssociatedWithUser(@Param("departmentId") Long departmentId);
}



