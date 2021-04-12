package com.kyeeego.TFood.configuration;

import com.kyeeego.TFood.modules.user.port.UserRepository;
import com.kyeeego.TFood.modules.user.usecase.CreateUser;
import com.kyeeego.TFood.modules.user.usecase.FindUser;
import lombok.RequiredArgsConstructor;
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

//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        final RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
//        return template;
//    }

}
