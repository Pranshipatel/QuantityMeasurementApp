package com.app.quantityservice.service;

public interface JWTService {
    String extractUserName(String token);
    boolean validateToken(String token);
}
