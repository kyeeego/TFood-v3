package com.kyeeego.TFood.filters;

import com.kyeeego.TFood.domain.exception.ForbiddenException;
import com.kyeeego.TFood.domain.exception.UnauthorizedException;
import com.kyeeego.TFood.usecase.auth.MyUserDetailsService;
import com.kyeeego.TFood.usecase.auth.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class JwtRequestFilter extends OncePerRequestFilter {

    private final MyUserDetailsService myUserDetailsService;
    private final JwtService jwtService;

    @Autowired
    public JwtRequestFilter(MyUserDetailsService myUserDetailsService, JwtService jwtService) {
        this.myUserDetailsService = myUserDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }


        final String accessToken = authHeader.split(" ")[1];

        final String email = jwtService.extractEmail(accessToken);
        if (email == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(email);
        if (jwtService.validateToken(accessToken, userDetails)) {
            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
                    .buildDetails(httpServletRequest));

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

}
