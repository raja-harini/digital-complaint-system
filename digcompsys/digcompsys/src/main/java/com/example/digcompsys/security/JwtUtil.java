package com.example.digcompsys.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET = "digcompsyssecretkeydigcompsyssecretkey";
    private final long EXPIRATION = 86400000;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // ✅ UPDATED METHOD (now includes userId)
    public String generateToken(Long userId, String username, String role) {

        Map<String, Object> claims = new HashMap<>();

        // ✅ ADD CUSTOM CLAIMS
        claims.put("role", role);
        claims.put("userId", userId); // 🔥 THIS FIXES YOUR ISSUE

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username) // email or username
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {

        return getClaims(token).getSubject();
    }

    // ✅ NEW METHOD (extract userId easily if needed in backend)
    public Long extractUserId(String token) {

        Object userId = getClaims(token).get("userId");
        return userId != null ? Long.parseLong(userId.toString()) : null;
    }

    // ✅ NEW METHOD (extract role)
    public String extractRole(String token) {

        return (String) getClaims(token).get("role");
    }

    public boolean validateToken(String token, String username) {

        String extracted = extractUsername(token);
        return extracted.equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {

        Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    // ✅ COMMON METHOD (avoid repetition)
    private Claims getClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}