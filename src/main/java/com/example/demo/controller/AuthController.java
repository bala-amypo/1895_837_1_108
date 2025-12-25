package com.example.demo.controller;

import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomUserDetailsService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(CustomUserDetailsService userService,
                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> req) {
        return userService.registerUser(
                req.get("name"),
                req.get("email"),
                encoder.encode(req.get("password")),
                req.get("role")
        );
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> req) {

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        req.get("email"),
                        req.get("password"),
                        Collections.emptyList()
                );

        Map<String, Object> user =
                userService.registerUser(
                        "TEMP",
                        req.get("email"),
                        encoder.encode(req.get("password")),
                        "USER"
                );

        String token = jwtTokenProvider.generateToken(
                auth,
                (Long) user.get("userId"),
                (String) user.get("role")
        );

        return Map.of(
                "token", token,
                "email", req.get("email")
        );
    }
}
