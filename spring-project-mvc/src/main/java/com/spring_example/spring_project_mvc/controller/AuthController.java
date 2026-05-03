package com.spring_example.spring_project_mvc.controller;

import com.spring_example.spring_project_mvc.model.User;
import com.spring_example.spring_project_mvc.model.dto.AuthResponse;
import com.spring_example.spring_project_mvc.model.dto.LoginRequest;
import com.spring_example.spring_project_mvc.model.dto.RegisterRequest;
import com.spring_example.spring_project_mvc.repo.UserRepo;
import com.spring_example.spring_project_mvc.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        if (userRepo.findByUsername(request.username()).isPresent()) {
            return new ResponseEntity<>("Username already taken", HttpStatus.CONFLICT);
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        // Accept "ADMIN" or "USER" from frontend, store as "ROLE_ADMIN" or "ROLE_USER"
        String role = request.role().equalsIgnoreCase("ADMIN") ? "ROLE_ADMIN" : "ROLE_USER";
        user.setRole(role);

        userRepo.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        User user = userRepo.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return new ResponseEntity<>(
                new AuthResponse(token, user.getRole(), user.getUsername()),
                HttpStatus.OK
        );
    }
}