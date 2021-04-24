package com.kyeeego.TFood.application.service.auth;

import com.kyeeego.TFood.domain.dto.auth.TokenPair;
import com.kyeeego.TFood.application.port.auth.AuthService;
import com.kyeeego.TFood.application.port.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDetailsService myUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final SessionService sessionService;

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
