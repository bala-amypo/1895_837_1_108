package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/token")
    public String generateToken(@RequestBody Map<String, String> login) {

        String username = login.get("username");
        String password = login.get("password");

        return "Token generated successfully for user: " + username;
    }
}
