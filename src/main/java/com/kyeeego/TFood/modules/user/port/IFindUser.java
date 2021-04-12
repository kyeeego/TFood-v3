package com.kyeeego.TFood.modules.user.port;

import com.kyeeego.TFood.modules.user.entity.User;
import com.kyeeego.TFood.modules.user.entity.dto.UserResponse;

import java.util.List;

public interface IFindUser {
    List<User> all();
    User byID(String id);
}
