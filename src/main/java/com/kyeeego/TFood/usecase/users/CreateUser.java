package com.kyeeego.TFood.usecase.users;

import com.kyeeego.TFood.domain.entity.user.User;
import com.kyeeego.TFood.domain.entity.user.dto.CreatedUserResponse;
import com.kyeeego.TFood.domain.entity.user.dto.UserCreateDto;
import com.kyeeego.TFood.domain.exception.BadRequestException;
import com.kyeeego.TFood.domain.exception.user.UserAlreadyExistsException;
import com.kyeeego.TFood.domain.port.UserRepository;
import com.kyeeego.TFood.usecase.users.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class CreateUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreateUser(UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CreatedUserResponse create(UserCreateDto userInput) {
        User user = new User(userInput);

        if (userRepository
                .findByEmail(user.getEmail())
                .isPresent()
        )
            throw new UserAlreadyExistsException();

        if (!UserValidator.validateCreate(user))
            throw new BadRequestException("Couldn't validate user");

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        final String refreshToken = UUID.randomUUID().toString();
        user.setRefreshToken(
                passwordEncoder.encode(refreshToken)
        );
        userRepository.save(user);

        user.setRefreshToken(refreshToken);

        return new CreatedUserResponse(user);
    }
}
