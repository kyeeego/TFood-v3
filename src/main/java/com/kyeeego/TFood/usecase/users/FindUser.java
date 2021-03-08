package com.kyeeego.TFood.usecase.users;

import com.kyeeego.TFood.domain.entity.user.User;
import com.kyeeego.TFood.domain.exception.user.UserNotFoundException;
import com.kyeeego.TFood.domain.port.UserRepository;
import com.kyeeego.TFood.domain.entity.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public final class FindUser {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> all() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public UserResponse byID(String id) {
        return new UserResponse(
                userRepository
                        .findById(id)
                        .orElseThrow(UserNotFoundException::new)
        );

    }
}
