package com.kyeeego.TFood.controller;

import com.kyeeego.TFood.application.port.ActivityService;
import com.kyeeego.TFood.domain.dto.activity.AddActivityDto;
import com.kyeeego.TFood.domain.dto.activity.AddProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/water")
    public void addWater(Principal principal, @RequestParam("amount") int amount) {
        activityService.addWater(principal, amount);
    }

    @PostMapping("/sleep")
    public void addSleep(Principal principal, @RequestParam("time") int time) {
        activityService.setSleepTime(principal, time);
    }

    @PostMapping("/eating")
    public void addEating(Principal principal, @RequestBody @Valid AddProductDto addProductDto) {
        activityService.addProduct(principal, addProductDto);
    }

    @PostMapping
    public void addActivity(Principal principal, @RequestBody @Valid AddActivityDto addActivityDto) {
        activityService.addActivity(principal, addActivityDto);
    }

}
