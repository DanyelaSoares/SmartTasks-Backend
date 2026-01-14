package com.smarttasks.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // a chave PRECISA ter ao menos 32 bytes (256 bits)
    private static final String SECRET = "segredo_super_secreto_seguro_grande_123";

    private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(SIGNING_KEY) // <- não passa algoritmo manualmente
                .compact();
    }
}
