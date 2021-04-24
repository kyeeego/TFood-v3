package com.kyeeego.TFood.modules.auth.usecase;

import com.kyeeego.TFood.modules.user.entity.User;
import com.kyeeego.TFood.modules.user.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException(username);
        final User us = user.get();

        return new org.springframework.security.core.userdetails.User(
                us.getEmail(),
                us.getPassword(),
                new ArrayList<>()
        );
    }
}