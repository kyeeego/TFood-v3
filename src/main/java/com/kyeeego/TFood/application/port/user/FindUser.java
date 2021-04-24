package com.kyeeego.TFood.application.port.user;

import com.kyeeego.TFood.domain.User;

import java.util.List;

public interface FindUser {
    List<User> all();
    User byID(String id);
}
