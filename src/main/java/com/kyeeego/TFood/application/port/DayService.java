package com.kyeeego.TFood.application.port;

import com.kyeeego.TFood.domain.Day;

import java.security.Principal;

public interface DayService {
    Day today(Principal principal);
    void rate(float rating, Principal principal);
}
