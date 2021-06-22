package com.kyeeego.TFood.domain.dto.user;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserCreateDto {

    @Email(message = "should be email")
    @NotNull(message = "Should be present")
    private String email;

    @NotNull
    @Min(-720)
    @Max(720)
    private int gmttimezone;

    @NotNull(message = "Should be present")
    private String password;

}


