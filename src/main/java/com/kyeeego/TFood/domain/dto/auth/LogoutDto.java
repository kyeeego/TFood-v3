package com.kyeeego.TFood.domain.dto.auth;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LogoutDto {
   @NotNull private String fingerprint;
}
