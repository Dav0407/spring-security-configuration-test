package com.example.rest_security_jwt_hometask.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final String SECRET_KEY = "b5f31911cca2fdff7313ae331e264a377b7d9d813835a1444419feaa51deedc9";

    public String generateToken(@NonNull String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setIssuer("https://igriss.io")
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .signWith(secretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key secretKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    public boolean isValid(@NonNull String token) {
        try {
            Claims claims = getClaims(token);
            Date expiration = claims.getExpiration();
            return expiration.after(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUsername(@NonNull String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
