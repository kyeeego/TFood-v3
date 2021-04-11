package com.kyeeego.TFood.modules.user.usecase;

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
