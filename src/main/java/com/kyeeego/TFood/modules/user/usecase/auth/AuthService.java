package com.kyeeego.TFood.modules.user.usecase.auth;

import com.kyeeego.TFood.modules.user.entity.User;
import com.kyeeego.TFood.modules.user.entity.dto.auth.AccessToken;
import com.kyeeego.TFood.modules.user.entity.dto.auth.LogInDto;
import com.kyeeego.TFood.modules.user.exception.UserNotFoundException;
import com.kyeeego.TFood.modules.user.port.IAuthService;
import com.kyeeego.TFood.modules.user.port.UserRepository;
import com.kyeeego.TFood.modules.user.usecase.auth.jwt.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    private final MyUserDetailsService myUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final AccessTokenService jwtService;
    private final UserRepository userRepository;

    @Autowired
    public AuthService(
            MyUserDetailsService myUserDetailsService,
            AuthenticationManager authenticationManager,
            AccessTokenService jwtService,
            UserRepository userRepository
    ) {
        this.myUserDetailsService = myUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
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

        // TODO: generate session

        final String accessToken = jwtService.generateToken(userDetails);

        User user = userRepository
                .findByEmail(logInDto.getEmail())
                .orElseThrow(UserNotFoundException::new);

        userRepository.save(user);

        return new AccessToken(accessToken);
    }

//    public AccessToken refresh(RefreshToken refreshToken) {
//        // TODO: Check session
//
//        return new AccessToken(accessToken);
//    }
}
