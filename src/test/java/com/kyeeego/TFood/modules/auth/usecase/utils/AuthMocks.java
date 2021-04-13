package com.kyeeego.TFood.modules.auth.usecase.utils;

import com.kyeeego.TFood.modules.session.entity.Session;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

public class AuthMocks {
    public static User User = new User("a@a.a",
            "$2a$13$PY4BSoGsB1qys6sOEGdovOr3IHKE29aFjC300f4gMeWGGs7ZMxUm2",
            new ArrayList<>());

    public static Session Session = new Session("a@a.a", 10034837483478374L, "");
    public static Session ExpiredSession = new Session("a@a.a", 100L, "");
}
