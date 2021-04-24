package com.kyeeego.TFood.application.port.auth;

import com.kyeeego.TFood.domain.dto.auth.TokenPair;

public interface AuthService {
    TokenPair auth(String email, String password, String fingerprint);

    TokenPair refreshTokens(String fingerprint, String refreshToken);

    void logout(String fingerprint);
}
