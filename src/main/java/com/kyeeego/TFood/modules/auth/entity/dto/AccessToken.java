package com.kyeeego.TFood.modules.auth.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AccessToken {

    @NotNull
    private String token;

}
