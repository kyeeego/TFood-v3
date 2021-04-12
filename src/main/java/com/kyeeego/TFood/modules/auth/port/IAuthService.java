package com.kyeeego.TFood.modules.auth.port;

import com.kyeeego.TFood.modules.auth.entity.dto.LogInDto;
import com.kyeeego.TFood.modules.auth.entity.dto.RefreshDto;
import com.kyeeego.TFood.modules.auth.entity.dto.TokenPair;

public interface IAuthService {
    TokenPair auth(LogInDto logInDto);

    TokenPair refreshTokens(RefreshDto refreshDto);

    void logout(String fingerprint);
}
