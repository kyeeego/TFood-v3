package com.kyeeego.TFood.controller;

import com.kyeeego.TFood.application.port.UserService;
import com.kyeeego.TFood.domain.models.User;
import com.kyeeego.TFood.domain.dto.user.UserCreateDto;
import com.kyeeego.TFood.domain.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponse findByID(@PathVariable("id") String id) {
        return new UserResponse(userService.findById(id));
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody UserCreateDto userCreateDto) {
        User u = userService.create(userCreateDto);
        log.info("User " + u.getId() + " has successfully registered email " + u.getEmail());
        return new UserResponse(u);
    }

    @PostMapping("/ping")
    public String ping() {
        return "Pong!";
    }

}
