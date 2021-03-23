package com.kyeeego.TFood.modules.user.port;

import com.kyeeego.TFood.modules.user.entity.dto.UserResponse;

import java.util.List;

public interface IFindUser {
    List<UserResponse> all();
    UserResponse byID(String id);
}
