package com.kyeeego.TFood.adapter.controller;

import com.kyeeego.TFood.domain.entity.user.User;
import com.kyeeego.TFood.usecase.users.CreateUser;
import com.kyeeego.TFood.usecase.users.FindUser;
import com.kyeeego.TFood.domain.entity.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private FindUser findUser;
    private CreateUser createUser;

    @GetMapping
    public List<UserResponse> findAll() {
        return findUser.all();
    }

    @GetMapping("/{id}")
    public UserResponse findByID(@PathVariable("id") String id) {
        return findUser.byID(id);
    }

}
