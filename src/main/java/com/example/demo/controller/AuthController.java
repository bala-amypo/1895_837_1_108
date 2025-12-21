package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.ApiResponse;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtProvider;
    private final UserService userService;

    public AuthController(AuthenticationManager authManager,
                          JwtTokenProvider jwtProvider,
                          UserService userService) {
        this.authManager = authManager;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody User user) {
        User saved = userService.save(user);
        return ResponseEntity.ok(
                new ApiResponse(true, "User registered successfully", saved)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userService.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtProvider.generateToken(user.getEmail());

        return ResponseEntity.ok(
                new AuthResponse(token, user.getId(), user.getEmail(), user.getRole())
        );
    }
}
