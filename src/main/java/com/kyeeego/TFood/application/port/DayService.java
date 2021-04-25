package com.kyeeego.TFood.application.port;

import com.kyeeego.TFood.domain.Day;

import java.security.Principal;
import java.util.List;

public interface DayService {
    Day today(Principal principal);
    void rate(float rating, Principal principal);
    List<Day> pastWeek(Principal principal);
}
