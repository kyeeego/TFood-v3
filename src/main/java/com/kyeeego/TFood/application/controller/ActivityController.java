package com.kyeeego.TFood.application.controller;

import com.kyeeego.TFood.application.port.ActivityService;
import com.kyeeego.TFood.domain.dto.activity.AddActivityDto;
import com.kyeeego.TFood.domain.dto.activity.AddProductDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @ApiOperation("Добавить воду")
    @PostMapping("/water")
    public void addWater(Principal principal, @RequestParam("amount") int amount) {
        activityService.addWater(principal, amount);
    }

    @ApiOperation("Изменить длину сна (все значения в дне пересчитаются)")
    @PostMapping("/sleep")
    public void addSleep(Principal principal, @RequestParam("time") int time) {
        activityService.setSleepTime(principal, time);
    }

    @ApiOperation("Добавить приём пищи")
    @PostMapping("/eating")
    public void addEating(Principal principal, @RequestBody @Valid AddProductDto addProductDto) {
        activityService.addProduct(principal, addProductDto);
    }

    @ApiOperation("Добавить активность")
    @PostMapping
    public void addActivity(Principal principal, @RequestBody @Valid AddActivityDto addActivityDto) {
        activityService.addActivity(principal, addActivityDto);
    }

}
