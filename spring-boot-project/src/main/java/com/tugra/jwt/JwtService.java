package com.tugra.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "Kskr+MAvVu9RH4Kx4LHlIHtLMNLmqi5CEQ7hxn4QGgM=";

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(getKey() , SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token){

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();

        return claims;
    }

    public <T> T expertToken(String token , Function<Claims , T> claimsTFunction){

        Claims claims = getClaims(token);
        return claimsTFunction.apply(claims);

    }

    public String getUsernameByToken(String token){
        return expertToken(token, Claims::getSubject);
    }

    public boolean validateToken(String token){
        Date expiredDate = expertToken(token, Claims::getExpiration);
        return new Date().before(expiredDate);
    }

    public Key getKey(){
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
