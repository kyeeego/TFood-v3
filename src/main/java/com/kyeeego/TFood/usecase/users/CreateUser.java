package com.kyeeego.TFood.usecase.users;

import com.kyeeego.TFood.domain.entity.user.User;
import com.kyeeego.TFood.domain.exception.user.UserAlreadyExistsException;
import com.kyeeego.TFood.domain.port.IPasswordService;
import com.kyeeego.TFood.usecase.users.validator.UserValidator;
import com.kyeeego.TFood.domain.port.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public final class CreateUser {

    @Autowired
    private UserRepository userRepository;
    private IPasswordService passwordService;
    private UserValidator userValidator;

    public User create(User user) {
//        userValidator.validateCreateUser(user);

        if (userRepository
                .findByEmail(user.getEmail())
                .isPresent()
        )
            throw new UserAlreadyExistsException();

        user.setPassword(
                passwordService.hash(user.getPassword())
        );

        return userRepository.save(user);
    }
}
