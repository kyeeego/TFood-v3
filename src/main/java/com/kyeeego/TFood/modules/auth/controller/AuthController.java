package com.kyeeego.TFood.modules.auth.controller;

import com.kyeeego.TFood.modules.auth.entity.dto.LogInDto;
import com.kyeeego.TFood.modules.auth.entity.dto.RefreshDto;
import com.kyeeego.TFood.modules.auth.entity.dto.TokenPair;
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
    public TokenPair logIn(@RequestBody @Valid LogInDto logInDto) {
        return authService.auth(logInDto);
    }

    @PostMapping("/refresh")
    public TokenPair refreshAccessToken(@RequestBody @Valid RefreshDto refreshToken) {
        return authService.refreshTokens(refreshToken);
    }
}
