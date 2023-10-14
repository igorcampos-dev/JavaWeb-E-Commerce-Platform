package br.com.marketupdate.configuration;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;

public class TokenAuthenticator {
    private final String secretKey;
    public TokenAuthenticator(String secretKey) {
        this.secretKey = secretKey;
    }
    public String authenticateAndGetToken(String id) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 3600000);
        SecretKey secretKeys = Keys.hmacShaKeyFor(this.secretKey.getBytes());
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(String.valueOf(id))
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(secretKeys, SignatureAlgorithm.HS256);
        return jwtBuilder.compact();
    }
}
