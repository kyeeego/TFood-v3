package com.kyeeego.TFood.modules.user.usecase;

import com.kyeeego.TFood.modules.user.entity.User;
import com.kyeeego.TFood.modules.user.exception.UserNotFoundException;
import com.kyeeego.TFood.modules.user.port.FindUser;
import com.kyeeego.TFood.modules.user.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindUserImpl implements FindUser {

    private final UserRepository userRepository;

    public List<User> all() {
        return userRepository.findAll();
    }

    public User byID(String id) {
        return userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
