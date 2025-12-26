package com.example.demo.controller;

import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthController(CustomUserDetailsService userDetailsService,
                          JwtTokenProvider jwtTokenProvider) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // ✅ REGISTER USER
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> request) {

        String name = request.get("name");
        String email = request.get("email");
        String password = request.get("password");
        String role = request.get("role");

        String encodedPassword = passwordEncoder.encode(password);

        return userDetailsService.registerUser(
                name,
                email,
                encodedPassword,
                role
        );
    }

    // ✅ LOGIN USER → GENERATE JWT
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        // validate user exists
        userDetailsService.loadUserByUsername(email);

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        email,
                        password,
                        Collections.emptyList()
                );

        Map<String, Object> user =
                userDetailsService.getUserByEmail(email);

        String token = jwtTokenProvider.generateToken(
                authentication,
                (Long) user.get("userId"),
                (String) user.get("role")
        );

        return Map.of(
                "token", token,
                "userId", user.get("userId"),
                "role", user.get("role"),
                "email", email
        );
    }
}
