package com.kyeeego.TFood.application.service;

import com.kyeeego.TFood.exception.ExpiredException;
import com.kyeeego.TFood.exception.UnauthorizedException;
import com.kyeeego.TFood.domain.dto.auth.TokenPair;
import com.kyeeego.TFood.application.port.auth.AccessTokenService;
import com.kyeeego.TFood.domain.models.Session;
import com.kyeeego.TFood.application.repository.SessionRepository;
import com.kyeeego.TFood.application.port.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository repository;
    private final AccessTokenService accessTokenService;
    private final UserDetailsService myUserDetailsService;

    @Override
    public TokenPair create(UserDetails userDetails, String fingerprint) {
        if (getAmountOfSessions(userDetails.getUsername()) >= 3)
            repository.deleteByUserEmail(userDetails.getUsername());

        Session session = new Session(
                userDetails.getUsername(),
                System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30,
                fingerprint
        );
        final String refreshToken = UUID.randomUUID().toString();
        session.setRefreshToken(refreshToken);

        repository.save(session);

        final String accessToken = accessTokenService.generateToken(userDetails);

        return new TokenPair(accessToken, refreshToken);
    }

    @Override
    public TokenPair renew(String fingerprint, String refreshToken) {
        Session session = repository
                .findByRefreshToken(refreshToken)
                .orElseThrow(UnauthorizedException::new);

        repository.delete(session);

        if (isExpired(session))
            throw new ExpiredException("Refresh token expired! Have to relogin");

        if (!isValidFingerprint(session, fingerprint))
            throw new UnauthorizedException("Invalid fingerprint");

        final UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(session.getUserEmail());

        final String newRefreshToken = UUID.randomUUID().toString();
        final String newAccessToken = accessTokenService.generateToken(userDetails);

        Session newSession = new Session(
                userDetails.getUsername(),
                System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30,
                fingerprint
        );
        newSession.setRefreshToken(newRefreshToken);

        repository.save(newSession);

        return new TokenPair(newAccessToken, newRefreshToken);
    }

    @Override
    public void logout(String fingerprint) {
        repository.deleteByFingerprint(fingerprint);
    }

    private int getAmountOfSessions(String userEmail) {
        return repository.findByUserEmail(userEmail).size();
    }

    private boolean isExpired(Session s) {
        return s.getExpiresAt() <= System.currentTimeMillis();
    }

    private boolean isValidFingerprint(Session s, String fingerprint) {
        return s.getFingerprint().equals(fingerprint);
    }

}
