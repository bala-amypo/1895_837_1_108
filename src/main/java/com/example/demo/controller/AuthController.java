package com.example.demo.controller;

import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // ✅ MANUAL CREATION (NO SPRING INJECTION)
    private final JwtTokenProvider jwtTokenProvider =
            new JwtTokenProvider(
                    "VerySecretKeyForJwtDemoApplication123456",
                    3600000L,
                    true
            );

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String role = request.getOrDefault("role", "USER");

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        Collections.emptyList()
                );

        Long userId = Math.abs(email.hashCode()) * 1L;

        String token = jwtTokenProvider.generateToken(
                authentication,
                userId,
                role
        );

        return Map.of(
                "token", token,
                "email", email,
                "role", role
        );
    }
}
