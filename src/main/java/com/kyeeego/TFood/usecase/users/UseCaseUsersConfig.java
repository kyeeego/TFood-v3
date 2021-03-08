package com.kyeeego.TFood.usecase.users;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseUsersConfig {

    @Bean
    public FindUser findUserSetUp() {
        return new FindUser();
    }

    @Bean
    public CreateUser createUserSetUp() {
        return new CreateUser();
    }

}
