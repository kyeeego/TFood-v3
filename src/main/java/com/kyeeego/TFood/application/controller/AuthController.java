package com.kyeeego.TFood.application.controller;

import com.kyeeego.TFood.domain.dto.auth.LogInDto;
import com.kyeeego.TFood.domain.dto.auth.LogoutDto;
import com.kyeeego.TFood.domain.dto.auth.RefreshDto;
import com.kyeeego.TFood.domain.dto.auth.TokenPair;
import com.kyeeego.TFood.application.port.auth.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ApiOperation("Войти в систему")
    @PostMapping
    public TokenPair logIn(@RequestBody @Valid LogInDto logInDto) {
        return authService.auth(logInDto.getEmail(),
                logInDto.getPassword(),
                logInDto.getFingerprint());
    }

    @ApiOperation("Обновить токены")
    @PostMapping("/refresh")
    public TokenPair refreshAccessToken(@RequestBody @Valid RefreshDto refreshToken) {
        return authService.refreshTokens(refreshToken.getFingerprint(), refreshToken.getToken());
    }

    // TODO: google how to improve logout
    @ApiOperation("(SECURED) Стереть сессию данного устройства")
    @PostMapping("/logout")
    public void logout(@RequestBody @Valid LogoutDto body) {
        authService.logout(body.getFingerprint());
    }
}
