package com.nicogmerz4.portfolio.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collections;
import java.util.Date;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class TokenUtils {
    private final static String ACCESS_TOKEN_SECRET = "Y29udHJhc2VuYS1zdXBlclNFQ1JFVEFYRFhE";
    private final static Long ACCESS_TOKEN_SECONDS_DURATION = 31_536_000L;
    
    public static String createToken() {
        Long expirationTimeMS = ACCESS_TOKEN_SECONDS_DURATION * 1_000L;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeMS);
        
        return Jwts.builder()
                .setSubject("admin")
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }
    
    public static UsernamePasswordAuthenticationToken getAuth(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        
            String user = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }
}
