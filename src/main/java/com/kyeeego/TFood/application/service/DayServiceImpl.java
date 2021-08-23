package com.kyeeego.TFood.application.service;

import com.kyeeego.TFood.application.repository.UserRepository;
import com.kyeeego.TFood.domain.models.Day;
import com.kyeeego.TFood.application.repository.DayRepository;
import com.kyeeego.TFood.application.port.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public final class DayServiceImpl implements DayService {

    private final DayRepository dayRepository;
    private final UserRepository userRepository;
    private final Clock clock;

    @Override
    public Day today(Principal principal) {
        int userTimezone = userRepository.findByEmail(principal.getName())
                .orElseThrow()
                .getGmttimezone();

        long userTime = System.currentTimeMillis() + userTimezone * 60 * 1000L;

        LocalDate now = Instant.ofEpochMilli(userTime).atZone(ZoneId.of("GMT")).toLocalDate();

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

    @Override
    public List<Day> pastWeek(Principal principal) {
        LocalDate monday = LocalDate.now(clock).with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        List<Day> wholeDayHistory = dayRepository.findByUser(principal.getName());

        return wholeDayHistory.stream()
                .filter((day) -> monday.equals(day.getDate()) || monday.isBefore(day.getDate()))
                .collect(Collectors.toList());
    }


    // FIXME
//    @Override
//    public List<Day> pastWeek(Principal principal) {
//        LocalDate sunday = LocalDate.now(clock).with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
//        return dayRepository.findByUserAndDateAfter(principal.getName(), sunday);
//    }

    @Override
    public void update(Day day) {
        dayRepository.save(day);
    }
}
