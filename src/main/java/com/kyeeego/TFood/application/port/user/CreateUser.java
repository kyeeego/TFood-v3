package com.kyeeego.TFood.application.port.user;

import com.kyeeego.TFood.domain.User;
import com.kyeeego.TFood.domain.dto.user.UserCreateDto;

public interface CreateUser {
    User create(UserCreateDto userCreateDto);
}
