package com.carwash.userservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Securely generate a key for signing the JWT
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Generate JWT Token
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email) // Set the subject (email)
                .claim("role", role) // Add role claim
                .setIssuedAt(new Date()) // Set issued time
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
                .signWith(SECRET_KEY) // Sign with the dynamically generated secret key
                .compact();
    }

    // Extract Email from Token
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Extract Role from Token
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // Validate Token
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY) // Use the dynamically generated secret key
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Invalid JWT Token: " + e.getMessage());
            return false;
        }
    }

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY) // Use the dynamically generated secret key
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Error parsing JWT Token: " + e.getMessage());
        }
    }
}
