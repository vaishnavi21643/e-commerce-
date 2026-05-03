package com.spring_example.spring_project_mvc.model.dto;

public record AuthResponse(
        String token,
        String role,
        String username
) {}