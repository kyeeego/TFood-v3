package com.kyeeego.TFood.modules.user.entity.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenPair {

    private String accessToken;
    private String refreshToken;

}
