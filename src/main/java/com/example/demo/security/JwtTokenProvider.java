package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.Map;

public class JwtTokenProvider {

    private final String secret;
    private final long expiry;
    private final boolean enabled;

    public JwtTokenProvider(String secret, long expiry, boolean enabled) {
        this.secret = secret;
        this.expiry = expiry;
        this.enabled = enabled;
    }

    public String generateToken(Authentication auth, Long userId, String role) {
        return Jwts.builder()
                .setSubject(auth.getName())
                .claim("userId", userId)
                .claim("role", role)
                .claim("email", auth.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // ✅ FIXED FOR jjwt 0.9.1
    public String getUsernameFromToken(String token) {
        return (String) getAllClaims(token).get("email");
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Map<String, Object> getAllClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
