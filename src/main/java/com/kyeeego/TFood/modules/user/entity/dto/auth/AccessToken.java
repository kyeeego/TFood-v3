package com.kyeeego.TFood.modules.user.entity.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AccessToken {

    @NotNull
    private String token;

}
