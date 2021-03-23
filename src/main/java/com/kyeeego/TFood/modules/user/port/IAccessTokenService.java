package com.kyeeego.TFood.modules.user.port;

import org.springframework.security.core.userdetails.UserDetails;

public interface IAccessTokenService {
    String generateToken(UserDetails userDetails);
    boolean validateToken(String token, UserDetails userDetails);
    String extractEmail(String token);
}
