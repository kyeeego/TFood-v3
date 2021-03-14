package com.kyeeego.TFood.usecase.users;

import com.kyeeego.TFood.domain.entity.user.User;
import com.kyeeego.TFood.domain.entity.user.dto.CreatedUserResponse;
import com.kyeeego.TFood.domain.entity.user.dto.UserCreateDto;
import com.kyeeego.TFood.domain.exception.user.UserAlreadyExistsException;
import com.kyeeego.TFood.domain.port.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class CreateUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreateUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CreatedUserResponse create(UserCreateDto userInput) {
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

        final String refreshToken = UUID.randomUUID().toString();
        user.setRefreshToken(
                passwordEncoder.encode(refreshToken)
        );

        return new CreatedUserResponse(userRepository.save(user));
    }
}
