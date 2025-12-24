package com.example.demo.controller;

import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Swagger / Manual login endpoint
     * NOTE: userId and role are passed directly
     * (matches existing security code, no service changes)
     */
    @PostMapping("/token")
    public Map<String, String> login(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam Long userId,
            @RequestParam String role
    ) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(email, password)
                );

        String token =
                jwtTokenProvider.generateToken(authentication, userId, role);

        return Map.of("token", token);
    }
}
