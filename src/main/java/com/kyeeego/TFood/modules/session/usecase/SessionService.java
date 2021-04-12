package com.kyeeego.TFood.modules.session.usecase;

import com.kyeeego.TFood.exception.ExpiredException;
import com.kyeeego.TFood.exception.UnauthorizedException;
import com.kyeeego.TFood.modules.auth.entity.dto.TokenPair;
import com.kyeeego.TFood.modules.auth.port.IAccessTokenService;
import com.kyeeego.TFood.modules.auth.usecase.MyUserDetailsService;
import com.kyeeego.TFood.modules.session.entity.Session;
import com.kyeeego.TFood.modules.session.port.ISessionService;
import com.kyeeego.TFood.modules.session.port.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionService implements ISessionService {

    private final SessionRepository repository;
    private final IAccessTokenService accessTokenService;
    private final MyUserDetailsService myUserDetailsService;

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
