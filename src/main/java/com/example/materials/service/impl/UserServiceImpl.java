package com.example.materials.service.impl;

import com.example.materials.model.User;
import com.example.materials.repository.UserRepository;
import com.example.materials.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String rawPassword) {
        if (repo.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already taken");
        }
        User u = new User();
        u.setUsername(username);
        u.setPassword(passwordEncoder.encode(rawPassword));
        u.setRoles(Set.of("ROLE_USER"));
        return repo.save(u);
    }

    @Override
    public User findByUsername(String username) {
        return repo.findByUsername(username).orElse(null);
    }
}
