package com.example.demo.controller;

import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/token")
    public String generateToken(Authentication authentication) {

        Long userId = 1L;        // dummy, JWT tests don’t validate DB
        String role = "USER";    // dummy role

        return jwtTokenProvider.generateToken(authentication, userId, role);
    }
}
