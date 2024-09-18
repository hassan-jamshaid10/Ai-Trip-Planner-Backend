package com.aitripplanner.AiTripPlanner.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private final String SECRET_KEY = "17sXTzWDEwRSzRUeeHBGRHjTCr+vYmOaPJPn/PYfitrK6svQbWXyB+Dm5i8qB7Jeg8LlBzPYvqmoYy9UF3HzpTcbMuc6C5Ehw2LMm9Mq72FuTkHMzZBO9DjEv9hTKbcDfIPF4w+2Y5w9owJXPbRyu6ZKBu+R4jB61KxpK3TP5FQ1BoLXpl5edQJWwNMGTTDm4YjPhO8KAiKcNb4A4EhZ8U9Ws473w17L6TbO9CdwkktXbCp1rtkYgLm4DuPDdXT+lRAverk3oOM3CYtbpgB2T8frFdj8zp7FzWqKspxGjoNS0UlcA+aqZKq5MLstr9Oy52hIEmu2UQ+y+4IlH4EoGEjwEnPrzWV5sAoqYhUwkB8=";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
