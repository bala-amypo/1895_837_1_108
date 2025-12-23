package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, Map<String, Object>> users = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Map<String, Object> registerUser(
            String name,
            String email,
            String encodedPassword,
            String role) {

        Map<String, Object> userData = new HashMap<>();
        userData.put("userId", idGenerator.getAndIncrement());
        userData.put("name", name);
        userData.put("email", email);
        userData.put("password", encodedPassword);
        userData.put("role", role);

        users.put(email, userData);
        return userData;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Map<String, Object> user = users.get(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(
                (String) user.get("email"),
                (String) user.get("password"),
                Collections.singletonList(
                        new SimpleGrantedAuthority(
                                "ROLE_" + user.get("role")
                        )
                )
        );
    }
}
