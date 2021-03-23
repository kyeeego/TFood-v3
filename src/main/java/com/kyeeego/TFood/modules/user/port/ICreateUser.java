package com.kyeeego.TFood.modules.user.port;

import com.kyeeego.TFood.modules.user.entity.dto.UserCreateDto;
import com.kyeeego.TFood.modules.user.entity.dto.UserResponse;

public interface ICreateUser {
    UserResponse create(UserCreateDto userCreateDto);
}
