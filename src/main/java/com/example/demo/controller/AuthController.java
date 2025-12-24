package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/token")
    public String token() {
        return "JWT generation handled internally";
    }
}
