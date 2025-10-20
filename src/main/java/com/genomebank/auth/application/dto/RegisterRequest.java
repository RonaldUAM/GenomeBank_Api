package com.genomebank.auth.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    // Optional list of role names, e.g. ["USER","ADMIN"]
    private List<String> roles;
}
