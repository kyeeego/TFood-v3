package com.kyeeego.TFood.modules.auth.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LogoutDto {
   @NotNull private String fingerprint;
}
