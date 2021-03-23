package com.kyeeego.TFood.modules.auth.controller;

import com.kyeeego.TFood.modules.auth.entity.dto.AccessToken;
import com.kyeeego.TFood.modules.auth.entity.dto.LogInDto;
import com.kyeeego.TFood.modules.auth.port.IAuthService;
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
    private IAuthService authService;

    @PostMapping
    public AccessToken logIn(@RequestBody @Valid LogInDto logInDto) {
        return authService.auth(logInDto);
    }

//    @PostMapping("/refresh")
//    public AccessToken refreshAccessToken(@RequestBody @Valid RefreshToken refreshToken) {
//        return authService.refresh(refreshToken);
//    }
}
