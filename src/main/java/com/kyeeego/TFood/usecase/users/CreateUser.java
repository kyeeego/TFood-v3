package com.kyeeego.TFood.usecase.users;

import com.kyeeego.TFood.domain.entity.user.User;
import com.kyeeego.TFood.domain.entity.user.UserCreateDto;
import com.kyeeego.TFood.domain.exception.user.UserAlreadyExistsException;
import com.kyeeego.TFood.domain.port.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CreateUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(UserCreateDto userInput) {
//        userValidator.validateCreateUser(user);

        User user = new User(userInput);

        if (userRepository
                .findByEmail(user.getEmail())
                .isPresent()
        )
            throw new UserAlreadyExistsException();

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepository.save(user);
    }
}
