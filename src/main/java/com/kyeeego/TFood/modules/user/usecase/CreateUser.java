package com.kyeeego.TFood.modules.user.usecase;

import com.kyeeego.TFood.modules.user.entity.User;
import com.kyeeego.TFood.modules.user.entity.dto.UserCreateDto;
import com.kyeeego.TFood.modules.user.entity.dto.UserResponse;
import com.kyeeego.TFood.exception.BadRequestException;
import com.kyeeego.TFood.modules.user.exception.UserAlreadyExistsException;
import com.kyeeego.TFood.modules.user.port.ICreateUser;
import com.kyeeego.TFood.modules.user.port.UserRepository;
import com.kyeeego.TFood.modules.user.usecase.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class CreateUser implements ICreateUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(UserCreateDto userInput) {
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

        return user;
    }
}
