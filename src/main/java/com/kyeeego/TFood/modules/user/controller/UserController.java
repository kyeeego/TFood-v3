package com.kyeeego.TFood.modules.user.controller;

import com.kyeeego.TFood.modules.user.entity.User;
import com.kyeeego.TFood.modules.user.entity.dto.UserCreateDto;
import com.kyeeego.TFood.modules.user.port.ICreateUser;
import com.kyeeego.TFood.modules.user.port.IFindUser;
import com.kyeeego.TFood.modules.user.entity.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IFindUser findUser;
    private final ICreateUser createUser;

    @GetMapping
    public List<UserResponse> findAll() {
        return findUser.all()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponse findByID(@PathVariable("id") String id) {
        return new UserResponse(findUser.byID(id));
    }

    @PostMapping
    public UserResponse create(@Validated @RequestBody UserCreateDto userCreateDto) {
        User u = createUser.create(userCreateDto);
        log.info("User " + u.getId() + " has successfully registered email " + u.getEmail());
        return new UserResponse(u);
    }

    @PostMapping("/ping")
    public String ping() {
        return "Pong!";
    }

}
