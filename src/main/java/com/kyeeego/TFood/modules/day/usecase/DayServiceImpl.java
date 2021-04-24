package com.kyeeego.TFood.modules.day.usecase;

import com.kyeeego.TFood.modules.day.entity.Day;
import com.kyeeego.TFood.modules.day.port.DayRepository;
import com.kyeeego.TFood.modules.day.port.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DayServiceImpl implements DayService {

    private final DayRepository dayRepository;

    @Override
    public Day today(Principal principal) {
        // TODO: proper date calculation
        LocalDate now = LocalDate.now();
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
