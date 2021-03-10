package com.kyeeego.TFood.domain.entity.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserCreateDto {

    @Email(message = "should be email")
    @NotNull(message = "Should be present")
    private String email;

    @NotNull(message = "Should be present")
    private String password;

    @NotNull(message = "Should be present")
    private String username;

    @NotNull(message = "Should be present")
    private String birthdate;

    @NotNull(message = "Should be present")
    private int weight;

    @NotNull(message = "Should be present")
    private int height;

    @NotNull(message = "Should be present")
    private boolean gender;

    @NotNull(message = "Should be present")
    private int chest;

}


