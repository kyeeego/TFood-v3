package com.kyeeego.TFood.modules.session.port;

import com.kyeeego.TFood.modules.auth.entity.dto.TokenPair;
import org.springframework.security.core.userdetails.UserDetails;

public interface ISessionService {
    TokenPair create(UserDetails userDetails, String fingerprint);
    TokenPair renew(String fingerprint, String refreshToken);
    void logout(String fingerprint);
}
