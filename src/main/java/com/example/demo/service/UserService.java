package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    User save(User user);

    User getByEmail(String email);   // ✅ ADD THIS
}
