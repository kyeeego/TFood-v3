package com.kyeeego.TFood.configuration;

import com.kyeeego.TFood.modules.user.port.UserRepository;
import com.kyeeego.TFood.modules.user.usecase.CreateUser;
import com.kyeeego.TFood.modules.user.usecase.FindUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ServiceSetup {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public FindUser findUserSetUp() {
        return new FindUser(userRepository);
    }

    @Bean
    public CreateUser createUserSetUp() {
        return new CreateUser(userRepository, passwordEncoder);
    }

}
