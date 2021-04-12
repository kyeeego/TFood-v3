package com.kyeeego.TFood.modules.auth.controller;

import com.kyeeego.TFood.modules.auth.entity.dto.LogInDto;
import com.kyeeego.TFood.modules.auth.entity.dto.LogoutDto;
import com.kyeeego.TFood.modules.auth.entity.dto.RefreshDto;
import com.kyeeego.TFood.modules.auth.entity.dto.TokenPair;
import com.kyeeego.TFood.modules.auth.port.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping
    public TokenPair logIn(@RequestBody @Valid LogInDto logInDto) {
        return authService.auth(logInDto);
    }

    @PostMapping("/refresh")
    public TokenPair refreshAccessToken(@RequestBody @Valid RefreshDto refreshToken) {
        return authService.refreshTokens(refreshToken);
    }

    // TODO: get fingerprint from token
    @PostMapping("/logout")
    public void logout(@RequestBody @Valid LogoutDto body) {
        authService.logout(body.getFingerprint());
    }
}
