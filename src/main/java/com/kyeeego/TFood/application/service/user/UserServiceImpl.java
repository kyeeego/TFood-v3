package com.kyeeego.TFood.application.service.user;

import com.kyeeego.TFood.application.port.UserService;
import com.kyeeego.TFood.application.repository.UserRepository;
import com.kyeeego.TFood.domain.dto.user.UserCreateDto;
import com.kyeeego.TFood.domain.models.User;
import com.kyeeego.TFood.exception.BadRequestException;
import com.kyeeego.TFood.exception.UserAlreadyExistsException;
import com.kyeeego.TFood.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

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
