package com.kyeeego.TFood.modules.user.controller;

import com.kyeeego.TFood.modules.user.entity.dto.UserCreateDto;
import com.kyeeego.TFood.modules.user.port.ICreateUser;
import com.kyeeego.TFood.modules.user.port.IFindUser;
import com.kyeeego.TFood.modules.user.entity.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final IFindUser findUser;
    private final ICreateUser createUser;

    @Autowired
    public UserController(IFindUser findUser, ICreateUser createUser) {
        this.findUser = findUser;
        this.createUser = createUser;
    }

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
        return createUser.create(userCreateDto);

    }

    @PostMapping("/ping")
    public String ping() {
        return "Pong!";
    }

}
