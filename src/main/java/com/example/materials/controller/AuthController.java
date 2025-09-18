package com.example.materials.controller;

import com.example.materials.security.JwtTokenProvider;
import com.example.materials.service.UserService;
import com.example.materials.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtTokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO req) {
        User u = userService.register(req.getUsername(), req.getPassword());
        return ResponseEntity.ok(new AuthResponseDTO("User registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO req) {
        User u = userService.findByUsername(req.getUsername());
        if (u == null || !passwordEncoder.matches(req.getPassword(), u.getPassword())) {
            return ResponseEntity.status(401).body(new AuthResponseDTO("Invalid credentials"));
        }
        var token = tokenProvider.createToken(u.getUsername(), u.getRoles().stream().collect(Collectors.toList()));
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
