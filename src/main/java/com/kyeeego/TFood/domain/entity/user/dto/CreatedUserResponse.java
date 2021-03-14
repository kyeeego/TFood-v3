package com.kyeeego.TFood.domain.entity.user.dto;

import com.kyeeego.TFood.domain.entity.user.User;

public class CreatedUserResponse extends UserResponse {

    private final String refreshToken;

    public CreatedUserResponse(User user) {
        super(user);
        this.refreshToken = user.getRefreshToken();
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
