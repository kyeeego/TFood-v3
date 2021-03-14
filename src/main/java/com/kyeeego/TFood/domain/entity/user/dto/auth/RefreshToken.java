package com.kyeeego.TFood.domain.entity.user.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RefreshToken {

    @NotNull
    private String token;

    @NotNull
    private String id;

}
