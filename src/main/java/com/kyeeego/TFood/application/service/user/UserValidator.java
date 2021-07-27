package com.kyeeego.TFood.application.service.user;

import com.kyeeego.TFood.domain.models.User;

public class UserValidator {

    private final User user;

    public UserValidator(User user) {
        this.user = user;
    }

    public boolean validateCreate() {
        return validateHeightToGender();
    }

    private boolean validateHeightToGender() {
        return user.isGender()
                ? user.getHeight() >= 148 && user.getHeight() <= 193
                : user.getHeight() >= 148 && user.getHeight() <= 178;
    }

}
