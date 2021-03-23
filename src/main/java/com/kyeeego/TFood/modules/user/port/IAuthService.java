package com.kyeeego.TFood.modules.user.port;

import com.kyeeego.TFood.modules.user.entity.dto.auth.AccessToken;
import com.kyeeego.TFood.modules.user.entity.dto.auth.LogInDto;

public interface IAuthService {
    AccessToken auth(LogInDto logInDto);
}
