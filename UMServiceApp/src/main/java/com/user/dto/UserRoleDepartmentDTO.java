package com.user.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDepartmentDTO {

    private List<Long> roleIds;
    private List<Long> departmentIds;

    
}

