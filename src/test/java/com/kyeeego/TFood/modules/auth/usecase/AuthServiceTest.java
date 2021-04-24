package com.kyeeego.TFood.modules.auth.usecase;

import com.kyeeego.TFood.exception.ExpiredException;
import com.kyeeego.TFood.exception.UnauthorizedException;
import com.kyeeego.TFood.application.port.auth.AuthService;
import com.kyeeego.TFood.modules.auth.usecase.utils.AuthMocks;
import com.kyeeego.TFood.adapter.SessionRepository;
import com.kyeeego.TFood.exception.UserNotFoundException;
import com.kyeeego.TFood.adapter.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthServiceTest {

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private SessionRepository sessionRepository;
    @MockBean
    private UserDetailsService myUserDetailsService;

    @Autowired
    private AuthService authService;

    @BeforeEach
    void setUpMocks() {
        Mockito.doThrow(UserNotFoundException.class).when(myUserDetailsService)
                .loadUserByUsername(Mockito.anyString());
        Mockito.doReturn(AuthMocks.User).when(myUserDetailsService)
                .loadUserByUsername("a@a.a");

        AuthMocks.Session.setRefreshToken("__TOKEN__");
        AuthMocks.ExpiredSession.setRefreshToken("__TOKEN_EXP__");

        Mockito.doThrow(UnauthorizedException.class).when(sessionRepository)
                .findByRefreshToken(Mockito.anyString());
        Mockito.doReturn(Optional.of(AuthMocks.Session)).when(sessionRepository)
                .findByRefreshToken("__TOKEN__");
        Mockito.doReturn(Optional.of(AuthMocks.ExpiredSession)).when(sessionRepository)
                .findByRefreshToken("__TOKEN_EXP__");
    }

    @Test
    void auth() {
        assertThrows(BadCredentialsException.class, () -> authService.auth("no", "", ""));
        assertThrows(BadCredentialsException.class, () -> authService.auth("a@a.a", "asas", ""));
        assertDoesNotThrow(() -> authService.auth("a@a.a", "pass", ""));
    }

    @Test
    void refreshTokens() {
        assertThrows(UnauthorizedException.class, () -> authService.refreshTokens("", ""));
        assertThrows(ExpiredException.class, () -> authService.refreshTokens("", "__TOKEN_EXP__"));
        assertDoesNotThrow(() -> authService.refreshTokens("", "__TOKEN__"));
    }
}