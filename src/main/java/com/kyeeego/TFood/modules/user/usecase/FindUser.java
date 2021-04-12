package com.kyeeego.TFood.modules.user.usecase;

import com.kyeeego.TFood.modules.user.entity.User;
import com.kyeeego.TFood.modules.user.exception.UserNotFoundException;
import com.kyeeego.TFood.modules.user.port.IFindUser;
import com.kyeeego.TFood.modules.user.port.UserRepository;
import com.kyeeego.TFood.modules.user.entity.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindUser implements IFindUser {

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
