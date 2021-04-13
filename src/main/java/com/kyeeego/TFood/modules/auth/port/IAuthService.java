package com.kyeeego.TFood.modules.auth.port;

import com.kyeeego.TFood.modules.auth.entity.dto.LogInDto;
import com.kyeeego.TFood.modules.auth.entity.dto.RefreshDto;
import com.kyeeego.TFood.modules.auth.entity.dto.TokenPair;

public interface IAuthService {
    TokenPair auth(String email, String password, String fingerprint);

    TokenPair refreshTokens(String fingerprint, String refreshToken);

    void logout(String fingerprint);
}
