package com.kyeeego.TFood.modules.day.port;

import com.kyeeego.TFood.modules.day.entity.Day;

import java.security.Principal;

public interface DayService {
    Day today(Principal principal);
    void rate(float rating, Principal principal);
}
