package com.example.materials.controller;

import com.example.materials.model.User;
import com.example.materials.repository.UserRepository;
import com.example.materials.security.JwtTokenProvider;
import com.example.materials.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtTokenProvider tokenProvider;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void loginInvalidCredentialsReturns401() throws Exception {
        String body = "{\"username\":\"test\",\"password\":\"bad\"}";
        when(userService.findByUsername("test")).thenReturn(null);

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Invalid credentials")));
    }
}
