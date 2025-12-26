package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // ❌ Disable CSRF for APIs
            .csrf(csrf -> csrf.disable())

            // 🔐 Authorization rules
            .authorizeHttpRequests(auth -> auth

                // ✅ Allow Event APIs (THIS FIXES YOUR 403)
                .requestMatchers("/api/events/**").permitAll()

                // ✅ Allow Swagger
                .requestMatchers(
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html"
                ).permitAll()

                // ✅ Allow auth endpoints (if any)
                .requestMatchers("/auth/**").permitAll()

                // 🔒 Secure everything else
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
