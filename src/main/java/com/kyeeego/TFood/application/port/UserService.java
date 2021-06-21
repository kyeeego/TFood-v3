package com.kyeeego.TFood.application.port;

import com.kyeeego.TFood.domain.dto.user.UserCreateDto;
import com.kyeeego.TFood.domain.models.User;

import java.util.List;

public interface UserService {
    User create(UserCreateDto userCreateDto);
    List<User> findAll();
    User findById(String id);
}