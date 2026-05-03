package com.spring_example.spring_project_mvc.model.dto;

public record RegisterRequest(
        String username,
        String email,
        String password,
        String role
) {}