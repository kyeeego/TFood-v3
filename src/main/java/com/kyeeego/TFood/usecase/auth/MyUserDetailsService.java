package com.kyeeego.TFood.usecase.auth;

import com.kyeeego.TFood.domain.entity.user.User;
import com.kyeeego.TFood.domain.port.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

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
//                .withUsername(username)
//                .password(user.get().getPassword())
//                .build();
    }
}
