package com.kyeeego.TFood.usecase.auth;

import com.kyeeego.TFood.domain.entity.user.dto.auth.LogInDto;
import com.kyeeego.TFood.domain.entity.user.dto.auth.TokenPair;
import com.kyeeego.TFood.usecase.auth.jwt.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    private final MyUserDetailsService myUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final AccessTokenService jwtService;

    @Autowired
    public AuthService(
            MyUserDetailsService myUserDetailsService,
            AuthenticationManager authenticationManager,
            AccessTokenService jwtService
    ) {
        this.myUserDetailsService = myUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public TokenPair auth(LogInDto logInDto) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            logInDto.getEmail(),
                            logInDto.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Bad credentials");
        }


        final UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(logInDto.getEmail());

        final String accessToken = jwtService.generateToken(userDetails);
        final String refreshToken = UUID.randomUUID().toString();

        return new TokenPair(accessToken, refreshToken);
    }
}
