package com.kyeeego.TFood.application.port;

import com.kyeeego.TFood.domain.dto.auth.TokenPair;
import org.springframework.security.core.userdetails.UserDetails;

public interface SessionService {
    TokenPair create(UserDetails userDetails, String fingerprint);
    TokenPair renew(String fingerprint, String refreshToken);
    void logout(String fingerprint);
}
