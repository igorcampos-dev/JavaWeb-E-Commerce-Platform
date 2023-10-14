package br.com.marketupdate.service;

import br.com.marketupdate.configuration.TokenAuthenticator;
import br.com.marketupdate.configuration.TokenDecoder;
import io.jsonwebtoken.Claims;

public class TokenService {
    private static final String SECRET_KEY = "6j5Wd9TpRvAqCzE2yHmS7nG4f2o5F1B3p9J3EX8bLn5ZKuYpM9dHjQ3wAqE";
    private final TokenDecoder tokenDecoder;
    private final TokenAuthenticator tokenAuthenticator;
    public TokenService() {
        this.tokenDecoder = new TokenDecoder(SECRET_KEY);
        this.tokenAuthenticator = new TokenAuthenticator(SECRET_KEY);
    }
    public Claims decodeToken(String token){
        return tokenDecoder.decodeToken(token);
    }
    public String authenticateAndGetToken(String id){
        return tokenAuthenticator.authenticateAndGetToken(id);
    }
}
