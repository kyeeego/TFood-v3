package com.kyeeego.TFood.modules.auth.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RefreshDto {

    @NotNull
    private String token;

    @NotNull
    private String fingerprint;

}
