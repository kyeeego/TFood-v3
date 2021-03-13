package com.kyeeego.TFood.configuration;

import com.kyeeego.TFood.usecase.users.CreateUser;
import com.kyeeego.TFood.usecase.users.FindUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ServiceSetup {

    @Bean
    public FindUser findUserSetUp() {
        return new FindUser();
    }

    @Bean
    public CreateUser createUserSetUp() {
        return new CreateUser();
    }

}
