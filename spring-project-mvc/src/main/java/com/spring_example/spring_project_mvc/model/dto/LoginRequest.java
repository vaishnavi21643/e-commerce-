package com.spring_example.spring_project_mvc.model.dto;

public record LoginRequest(
        String username,
        String password
) {}