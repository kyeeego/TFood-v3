package com.kyeeego.TFood.usecase.auth.jwt;

import com.kyeeego.TFood.domain.entity.user.User;
import com.kyeeego.TFood.domain.exception.UnauthorizedException;
import com.kyeeego.TFood.domain.exception.user.UserNotFoundException;
import com.kyeeego.TFood.domain.port.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
// Using JWTs without expiration
public class AccessTokenService {

    @Value("${accesstoken.secret}")
    private String key;

    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;

    @Autowired
    public AccessTokenService(UserRepository userRepository, PasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key generateKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String sub) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(sub)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(generateKey(), SignatureAlgorithm.HS256).compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String email = extractEmail(token);

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(UnauthorizedException::new);

        return email.equals(userDetails.getUsername())
                && bcryptEncoder.matches(token, user.getAccessToken());
    }

}
