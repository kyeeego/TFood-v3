package com.kyeeego.TFood;

import com.kyeeego.TFood.configuration.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class TFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(TFoodApplication.class, args);
	}

}
