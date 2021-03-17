package com.kyeeego.TFood.modules.user.usecase.users;

import com.kyeeego.TFood.modules.user.entity.User;
import com.kyeeego.TFood.modules.user.entity.dto.UserCreateDto;
import com.kyeeego.TFood.modules.user.entity.dto.UserResponse;
import com.kyeeego.TFood.exception.BadRequestException;
import com.kyeeego.TFood.modules.user.exception.UserAlreadyExistsException;
import com.kyeeego.TFood.modules.user.port.UserRepository;
import com.kyeeego.TFood.modules.user.usecase.users.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CreateUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreateUser(UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse create(UserCreateDto userInput) {
        User user = new User(userInput);

        if (userRepository
                .findByEmail(user.getEmail())
                .isPresent())
            throw new UserAlreadyExistsException();

        if (!UserValidator.validateCreate(user))
            throw new BadRequestException("Couldn't validate user");

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        userRepository.save(user);

        return new UserResponse(user);
    }
}
