package com.kyeeego.TFood.domain.entity.user.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class TokenPair {

    private String accessToken;
    private String refreshToken;

}
