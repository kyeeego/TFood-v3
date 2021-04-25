package com.kyeeego.TFood.application.service;

import com.kyeeego.TFood.adapter.UserRepository;
import com.kyeeego.TFood.domain.Day;
import com.kyeeego.TFood.adapter.DayRepository;
import com.kyeeego.TFood.application.port.DayService;
import com.kyeeego.TFood.domain.User;
import com.kyeeego.TFood.domain.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

@Service
@RequiredArgsConstructor
public class DayServiceImpl implements DayService {

    private final DayRepository dayRepository;
    private final UserRepository userRepository;

    @Override
    public Day today(Principal principal) {
        // user could not possibly be null so we can ignore the warning
        int userTimezone = userRepository.findByEmail(principal.getName())
                .get()
                .getGmttimezone();

        long userTime = System.currentTimeMillis() + userTimezone * 60 * 1000L;

        LocalDate now = Instant.ofEpochMilli(userTime).atZone(ZoneId.of("GMT")).toLocalDate();
        System.out.println(now);

        Optional<Day> today = dayRepository.findByUserAndDate(principal.getName(), now);
        if (today.isPresent())
            return today.get();

        Day day = new Day(principal.getName(), now);

        return dayRepository.save(day);
    }

    @Override
    public void rate(float rating, Principal principal) {
        Day day = today(principal);
        day.setRating(rating);
        dayRepository.save(day);
    }
}
