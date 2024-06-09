package com.example.diplomaproject.utils;

import com.example.diplomaproject.entities.OrderEntity;
import com.example.diplomaproject.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;

    public String generateOrderToken(OrderEntity order) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("orderId", order.getId());

        Date issuedDate = order.getPrescription().getCreatedDate();
        Date expiredDate = order.getPrescription().getExpiredDate();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(order.getId().toString())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String generateToken(UserEntity user) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = user.getRoles().stream().map(role -> role.getName().toString()).toList();
        claims.put("roles", rolesList);
        claims.put("id", user.getId());
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime.toMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    public Integer getOrderId(String token) {
        return getAllClaimsFromToken(token).get("id", Integer.class);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public Long getUserId(String token) {
        return getAllClaimsFromToken(token).get("id", Long.class);
    }
}
