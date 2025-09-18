package com.example.materials.service;

import com.example.materials.model.User;

public interface UserService {
    User register(String username, String rawPassword);
    User findByUsername(String username);
}
