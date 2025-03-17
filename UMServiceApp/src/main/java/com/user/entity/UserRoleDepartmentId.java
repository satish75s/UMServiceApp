package com.user.entity;



import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDepartmentId implements Serializable {
   
	
	private static final long serialVersionUID = 1L;

    private String userId;
    private Long roleId;
    private Long departmentId;

    // Getters, Setters, equals, and hashCode
}
