package com.kyeeego.TFood.domain.entity.user.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AccessToken {

    @NotNull
    private String token;

}
