package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtUtil {


    private static final Algorithm HMAC_256 = Algorithm.HMAC256("secret".getBytes());


    public static DecodedJWT verifyToken(String header) {
        if (header != null && header.startsWith("Bearer ")) {
            JWTVerifier jwtVerifier = JWT.require(HMAC_256).build();
            return jwtVerifier.verify(header.substring("Bearer ".length()));
        } else {
            throw new RuntimeException("token is invalid");

        }
    }


    public static String createAccessToken(String username, List roles) {
        Map<String, String> payload = new HashMap<>();
//        payload.put("userEmail", "aaa@aaa.com");
//        payload.put("userNo", "1234567");
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withClaim("roles", roles)
                .withPayload(payload)
                .sign(HMAC_256);
    }


    public static String createRefreshToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + (1440 * 14 * 60 * 1000)))//2주
                .sign(HMAC_256);
    }
}
