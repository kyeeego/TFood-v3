package com.kyeeego.TFood.controller;

import com.kyeeego.TFood.domain.models.Day;
import com.kyeeego.TFood.application.port.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/day")
@RequiredArgsConstructor
@Validated
public class DayController {

    private final DayService dayService;

    @GetMapping("/today")
    public Day today(Principal principal) {
        return dayService.today(principal);
    }

    @GetMapping("/rate")
    public void rate(Principal principal,
                     @RequestParam("v") @Min(0) @Max(5) float v) {
        dayService.rate(v, principal);
    }

    @GetMapping("/week")
    public List<Day> pastWeek(Principal principal) {
        return dayService.pastWeek(principal);
    }

}
