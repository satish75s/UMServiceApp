package com.user.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.entity.Role;
import com.user.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	 @Autowired
	    private RoleService roleService;

	    @PostMapping
	    public ResponseEntity<Role> createRole(@RequestBody Role role) {
	        Role createdRole = roleService.createRole(role);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
	    }

	    @GetMapping
	    public ResponseEntity<List<Role>> getAllRoles() {
	        List<Role> roles = roleService.getAllRoles();
	        return ResponseEntity.ok(roles);
	    }

	    @GetMapping("/{roleId}")
	    public ResponseEntity<Role> getRoleById(@PathVariable Long roleId) {
	        Role role = roleService.getRoleById(roleId);
	        return ResponseEntity.ok(role);
	    }

	   /* @DeleteMapping("/{roleId}")
	    public ResponseEntity<Void> deleteRole(@PathVariable Long roleId) {
	        roleService.deleteRole(roleId);
	        return ResponseEntity.noContent().build();
	    }*/
  
}
