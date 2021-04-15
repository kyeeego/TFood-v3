package com.kyeeego.TFood.modules.user.port;

import com.kyeeego.TFood.modules.user.entity.User;
import com.kyeeego.TFood.modules.user.entity.dto.UserCreateDto;
import com.kyeeego.TFood.modules.user.entity.dto.UserResponse;

public interface CreateUser {
    User create(UserCreateDto userCreateDto);
}
