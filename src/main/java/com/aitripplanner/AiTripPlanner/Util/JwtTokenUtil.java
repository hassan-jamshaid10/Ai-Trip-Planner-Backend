package com.aitripplanner.AiTripPlanner.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private static final String SECRET_KEY = "17sXTzWDEwRSzRUeeHBGRHjTCr+vYmOaPJPn/PYfitrK6svQbWXyB+Dm5i8qB7Jeg8LlBzPYvqmoYy9UF3HzpTcbMuc6C5Ehw2LMm9Mq72FuTkHMzZBO9DjEv9hTKbcDfIPF4w+2Y5w9owJXPbRyu6ZKBu+R4jB61KxpK3TP5FQ1BoLXpl5edQJWwNMGTTDm4YjPhO8KAiKcNb4A4EhZ8U9Ws473w17L6TbO9CdwkktXbCp1rtkYgLm4DuPDdXT+lRAverk3oOM3CYtbpgB2T8frFdj8zp7FzWqKspxGjoNS0UlcA+aqZKq5MLstr9Oy52hIEmu2UQ+y+4IlH4EoGEjwEnPrzWV5sAoqYhUwkB8=";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails, Map<String, Object> extraClaims) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails, new HashMap<>());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public UserDetails getUserDetails(String token, UserDetailsService userDetailsService) {
        String username = extractUsername(token);
        return userDetailsService.loadUserByUsername(username);
    }
}
