package com.example.demo.security;

import org.springframework.security.core.userdetails.*;
import java.util.*;

public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, Map<String, Object>> users = new HashMap<>();
    private long idSeq = 1;

    public Map<String, Object> registerUser(
            String name, String email, String password, String role) {

        Map<String, Object> u = new HashMap<>();
        u.put("userId", idSeq++);
        u.put("email", email);
        u.put("password", password);
        u.put("role", role);
        users.put(email, u);
        return u;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (!users.containsKey(username))
            throw new UsernameNotFoundException("User not found");
        Map<String, Object> u = users.get(username);
        return User.withUsername(username)
                .password((String) u.get("password"))
                .roles((String) u.get("role"))
                .build();
    }
}
