package com.kyeeego.TFood.usecase.auth;

import com.kyeeego.TFood.domain.entity.user.User;
import com.kyeeego.TFood.domain.entity.user.dto.auth.AccessToken;
import com.kyeeego.TFood.domain.entity.user.dto.auth.LogInDto;
import com.kyeeego.TFood.domain.entity.user.dto.auth.RefreshToken;
import com.kyeeego.TFood.domain.entity.user.dto.auth.TokenPair;
import com.kyeeego.TFood.domain.exception.ForbiddenException;
import com.kyeeego.TFood.domain.exception.UnauthorizedException;
import com.kyeeego.TFood.domain.exception.user.UserNotFoundException;
import com.kyeeego.TFood.domain.port.UserRepository;
import com.kyeeego.TFood.usecase.auth.jwt.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    private final MyUserDetailsService myUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final AccessTokenService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(
            MyUserDetailsService myUserDetailsService,
            AuthenticationManager authenticationManager,
            AccessTokenService jwtService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.myUserDetailsService = myUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AccessToken auth(LogInDto logInDto) {

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

        User user = userRepository
                .findByEmail(logInDto.getEmail())
                .orElseThrow(UserNotFoundException::new);

        user.setAccessToken(passwordEncoder.encode(accessToken));

        userRepository.save(user);

        return new AccessToken(accessToken);
    }

    public AccessToken refresh(RefreshToken refreshToken) {
        final User user = userRepository
                .findById(refreshToken.getId())
                .orElseThrow(UnauthorizedException::new);

        if (!passwordEncoder.matches(refreshToken.getToken(), user.getRefreshToken()))
            throw new ForbiddenException("Invalid refresh token");

        final String accessToken = jwtService.generateToken(
            myUserDetailsService
                .loadUserByUsername(user.getEmail())
        );

        user.setAccessToken(
                passwordEncoder.encode(accessToken)
        );
        userRepository.save(user);

        return new AccessToken(accessToken);
    }
}
