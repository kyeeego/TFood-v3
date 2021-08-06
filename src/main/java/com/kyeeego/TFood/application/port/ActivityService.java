package com.kyeeego.TFood.application.port;

import com.kyeeego.TFood.domain.dto.activity.AddActivityDto;
import com.kyeeego.TFood.domain.dto.activity.AddProductDto;
import com.kyeeego.TFood.domain.models.Day;
import com.kyeeego.TFood.domain.types.Eating;

import java.security.Principal;

public interface ActivityService {
    Day addWater(Principal principal, int amount);
    Day addProduct(Principal principal, AddProductDto addFoodDto);
    void deleteProduct(Principal principal, Eating eating, int id);
    void deleteActivity(Principal principal, int id);
    Day setSleepTime(Principal principal, int amount);
    Day addActivity(Principal principal, AddActivityDto addActivityDto);
}
