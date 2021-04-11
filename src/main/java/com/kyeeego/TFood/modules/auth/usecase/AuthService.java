package com.kyeeego.TFood.modules.auth.usecase;

import com.kyeeego.TFood.modules.auth.entity.dto.RefreshDto;
import com.kyeeego.TFood.modules.auth.entity.dto.TokenPair;
import com.kyeeego.TFood.modules.session.port.ISessionService;
import com.kyeeego.TFood.modules.auth.entity.dto.LogInDto;
import com.kyeeego.TFood.modules.auth.port.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final MyUserDetailsService myUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final ISessionService sessionService;

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

        return sessionService.create(userDetails, logInDto.getFingerprint());
    }

    @Override
    public TokenPair refreshTokens(RefreshDto refreshDto) {
        return sessionService.renew(
                refreshDto.getFingerprint(),
                refreshDto.getToken()
        );
    }

}
