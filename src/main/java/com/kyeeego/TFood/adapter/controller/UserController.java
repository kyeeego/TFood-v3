package com.kyeeego.TFood.adapter.controller;

import com.kyeeego.TFood.domain.entity.user.User;
import com.kyeeego.TFood.domain.entity.user.UserCreateDto;
import com.kyeeego.TFood.usecase.users.CreateUser;
import com.kyeeego.TFood.usecase.users.FindUser;
import com.kyeeego.TFood.domain.entity.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private FindUser findUser;

    @Autowired
    private CreateUser createUser;

    @GetMapping
    public List<UserResponse> findAll() {
        return findUser.all();
    }

    @GetMapping("/{id}")
    public UserResponse findByID(@PathVariable("id") String id) {
        return findUser.byID(id);
    }

    @PostMapping
    public UserResponse create(@Validated @RequestBody UserCreateDto userCreateDto) {
        return new UserResponse(
                createUser.create(userCreateDto)
        );
    }

}
