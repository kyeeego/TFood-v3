package com.kyeeego.TFood.usecase.users.validator;

import com.kyeeego.TFood.domain.entity.user.User;

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
