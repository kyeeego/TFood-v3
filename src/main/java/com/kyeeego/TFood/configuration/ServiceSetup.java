package com.kyeeego.TFood.configuration;

import com.kyeeego.TFood.modules.user.port.UserRepository;
import com.kyeeego.TFood.modules.user.usecase.CreateUser;
import com.kyeeego.TFood.modules.user.usecase.FindUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ServiceSetup {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ServiceSetup(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public FindUser findUserSetUp() {
        return new FindUser();
    }

    @Bean
    public CreateUser createUserSetUp() {
        return new CreateUser(userRepository, passwordEncoder);
    }

}
