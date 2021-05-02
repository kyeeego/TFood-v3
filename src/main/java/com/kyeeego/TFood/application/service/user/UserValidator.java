package com.kyeeego.TFood.application.service.user;

import com.kyeeego.TFood.domain.models.User;

public class UserValidator {

    public static boolean validateCreate(User user) {
        return validateHeightToGender(user);
    }

    private static boolean validateHeightToGender(User user) {
        return user.isGender()
                ? user.getHeight() >= 148 && user.getHeight() <= 193
                : user.getHeight() >= 148 && user.getHeight() <= 178;
    }

}
