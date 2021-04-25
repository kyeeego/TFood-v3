package com.kyeeego.TFood.application.service.user;

import com.kyeeego.TFood.domain.User;
import com.kyeeego.TFood.exception.UserNotFoundException;
import com.kyeeego.TFood.application.port.user.FindUser;
import com.kyeeego.TFood.adapter.repository.UserRepository;
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
