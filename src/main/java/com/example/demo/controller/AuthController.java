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

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Simple token generation endpoint for Swagger/demo
     * No AuthenticationManager required
     */
    @PostMapping("/token")
    public Map<String, String> generateToken(
            @RequestParam String email,
            @RequestParam Long userId,
            @RequestParam String role
    ) {

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
