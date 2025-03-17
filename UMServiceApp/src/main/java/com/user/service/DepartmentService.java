package com.user.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.Department;
import com.user.repository.DepartmentRepository;

@Service
public class DepartmentService {
	 @Autowired
	    private DepartmentRepository departmentRepository;

	    public Department createDepartment(Department department) {
	        if (departmentRepository.existsByDepartmentName(department.getDepartmentName())) {
	            throw new RuntimeException("Department with the same name already exists");
	        }
	        return departmentRepository.save(department);
	    }

	    public List<Department> getAllDepartments() {
	        return departmentRepository.findAll();
	    }

	    public Department getDepartmentById(Long departmentId) {
	        return departmentRepository.findById(departmentId)
	                .orElseThrow(() -> new RuntimeException("Department not found"));
	    }

	    public void deleteDepartment(Long departmentId) {
	        if (departmentRepository.isDepartmentAssociatedWithUser(departmentId)) {
	            throw new RuntimeException("Department is associated with users and cannot be deleted");
	        }
	        departmentRepository.deleteById(departmentId);
	    }
   
}

