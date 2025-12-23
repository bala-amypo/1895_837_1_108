package com.example.demo.security;

import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, User> users = new HashMap<>();
    private long idSequence = 1;

    public CustomUserDetailsService() {
    }

    public Map<String, Object> registerUser(
            String fullName,
            String email,
            String password,
            String role) {

        User user = new User();
        user.setId(idSequence++);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        users.put(email, user);

        Map<String, Object> response = new HashMap<>();
        response.put("userId", user.getId());
        response.put("role", user.getRole());

        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = users.get(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(
                        new SimpleGrantedAuthority(user.getRole()))
        );
    }
}
