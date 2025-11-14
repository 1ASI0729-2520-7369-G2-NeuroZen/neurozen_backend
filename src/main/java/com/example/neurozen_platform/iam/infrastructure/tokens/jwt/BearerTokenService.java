package com.example.neurozen_platform.iam.infrastructure.tokens.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface BearerTokenService {
  String generateToken(Authentication authentication);
  String generateToken(String username);
  String getUsernameFromToken(String token);
  boolean validateToken(String token);
  String getBearerTokenFrom(HttpServletRequest request);
}

