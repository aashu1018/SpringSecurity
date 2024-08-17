package com.SecurityApp.SecurityApplication.dto;

import com.SecurityApp.SecurityApplication.entities.enums.Permission;
import com.SecurityApp.SecurityApplication.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignupDTO {

    private String email;
    private String password;
    private String name;
    private Set<Role> roles;
    private Set<Permission> permissions;
}
