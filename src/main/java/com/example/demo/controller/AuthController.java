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

    @PostMapping("/token")
    public Map<String, String> generateToken(
            @RequestParam String email,
            @RequestParam Long userId,
            @RequestParam String role
    ) {

        // Same way as in your TESTS
        JwtTokenProvider jwtTokenProvider =
                new JwtTokenProvider(
                        "VerySecretKeyForJwtDemoApplication123456",
                        3600000L,
                        true
                );

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        Collections.emptyList()
                );

        String token =
                jwtTokenProvider.generateToken(auth, userId, role);

        return Map.of("token", token);
    }
}
