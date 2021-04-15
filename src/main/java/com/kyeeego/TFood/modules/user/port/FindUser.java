package com.kyeeego.TFood.modules.user.port;

import com.kyeeego.TFood.modules.user.entity.User;

import java.util.List;

public interface FindUser {
    List<User> all();
    User byID(String id);
}
