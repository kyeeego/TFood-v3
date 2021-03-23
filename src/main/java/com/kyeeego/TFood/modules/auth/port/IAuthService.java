package com.kyeeego.TFood.modules.auth.port;

import com.kyeeego.TFood.modules.auth.entity.dto.AccessToken;
import com.kyeeego.TFood.modules.auth.entity.dto.LogInDto;

public interface IAuthService {
    AccessToken auth(LogInDto logInDto);
}
