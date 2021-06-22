package com.kyeeego.TFood.application.port;

import com.kyeeego.TFood.domain.dto.user.UserCreateDto;
import com.kyeeego.TFood.domain.dto.user.UserUpdateDto;
import com.kyeeego.TFood.domain.dto.user.UserUpdateResponse;
import com.kyeeego.TFood.domain.models.User;

import java.security.Principal;
import java.util.List;

public interface UserService {
    User create(UserCreateDto userCreateDto);
    UserUpdateResponse update(Principal principal, UserUpdateDto userUpdateDto);
    List<User> findAll();
    User findById(String id);
}