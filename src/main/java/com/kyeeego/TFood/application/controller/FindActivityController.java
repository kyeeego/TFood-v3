package com.kyeeego.TFood.application.controller;

import com.kyeeego.TFood.application.port.ActivityFindService;
import com.kyeeego.TFood.domain.models.Activity;
import com.kyeeego.TFood.domain.models.Product;
import com.kyeeego.TFood.domain.types.ActivityType;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/find-activity")
@RequiredArgsConstructor
public class FindActivityController {

    private final ActivityFindService activityService;

    @ApiOperation("Поиск по активностям с запросом и типом (Спорт или Труд по дому)")
    @GetMapping
    public List<Activity> findAllActivities(@RequestParam("type") ActivityType type, @RequestParam("q") String q) {
        return activityService.findAllActivitiesWithQuery(type, q);
    }

//    @GetMapping("/{id}")
//    public Activity findActivityById(@PathVariable("id") String id) {
//        return activityService.findActivityById(id);
//    }
    @ApiOperation("Поиск по продуктам с запросом")
    @GetMapping("/products")
    public List<Product> findProductsByQuery(@RequestParam("q") String q) {
        return activityService.findProductsByQuery(q);
    }

//    @GetMapping("/products/{id}")
//    public Product findProductById(@PathVariable("id") String id) {
//        return activityService.findProductById(id);
//    }

}
