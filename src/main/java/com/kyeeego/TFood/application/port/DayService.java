package com.kyeeego.TFood.application.port;

import com.kyeeego.TFood.domain.models.Day;

import java.security.Principal;
import java.util.List;

public interface DayService {
    Day today(Principal principal);
    void rate(float rating, Principal principal);
    List<Day> pastWeek(Principal principal);
    void update(Day day);
}
