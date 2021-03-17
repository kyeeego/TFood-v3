package com.kyeeego.TFood.modules.user.entity.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RefreshToken {

    @NotNull
    private String token;

    @NotNull
    private String userId;

}
