package com.kyeeego.TFood.domain.dto.user;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserCreateDto {

    @Email(message = "should be email")
    @NotNull(message = "Should be present")
    private String email;

    @NotNull
//    @Pattern(regexp = "([+-]):(1[0-2]|0?[1-9]):([03]0)")
    @Min(-720)
    @Max(720)
    private int gmttimezone;

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


