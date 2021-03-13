package com.kyeeego.TFood.adapter.controller;

import com.kyeeego.TFood.domain.entity.user.dto.auth.LogInDto;
import com.kyeeego.TFood.domain.entity.user.dto.auth.TokenPair;
import com.kyeeego.TFood.usecase.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public TokenPair logIn(@RequestBody @Valid LogInDto logInDto) {
        return authService.auth(logInDto);
    }
}
