package com.kyeeego.TFood.modules.auth.usecase;

import com.kyeeego.TFood.modules.auth.entity.dto.RefreshDto;
import com.kyeeego.TFood.modules.auth.entity.dto.TokenPair;
import com.kyeeego.TFood.modules.session.port.ISessionService;
import com.kyeeego.TFood.modules.auth.entity.dto.LogInDto;
import com.kyeeego.TFood.modules.auth.port.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final MyUserDetailsService myUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final ISessionService sessionService;

    public TokenPair auth(String email, String password, String fingerprint) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            password
                    )
            );
        } catch (AuthenticationException e) {
            log.error("Authentiction failed for email: " + email);
            throw new BadCredentialsException("Bad credentials");
        }
        final UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(email);

        return sessionService.create(userDetails, fingerprint);
    }

    @Override
    public TokenPair refreshTokens(String fingerprint, String refreshToken) {
        return sessionService.renew(
                fingerprint,
                refreshToken
        );
    }

    @Override
    public void logout(String fingerprint) {
        sessionService.logout(fingerprint);
    }
}
