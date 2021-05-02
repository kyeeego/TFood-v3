package com.kyeeego.TFood.adapter.controller;

import com.kyeeego.TFood.application.port.ActivityService;
import com.kyeeego.TFood.domain.models.Activity;
import com.kyeeego.TFood.domain.models.Product;
import com.kyeeego.TFood.domain.types.ActivityType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/")
    public List<Activity> findAllActivities(@RequestParam("type") ActivityType type, @RequestParam("q") String q) {
        return activityService.findAllActivitiesWithQuery(type, q);
    }

    @GetMapping("/{id}")
    public Activity findActivityById(@PathVariable("id") String id) {
        return activityService.findActivityById(id);
    }

    @GetMapping("/products")
    public List<Product> findProductsByQuery(@RequestParam("q") String q) {
        return activityService.findProductsByQuery(q);
    }

    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable("id") String id) {
        return activityService.findProductById(id);
    }

}