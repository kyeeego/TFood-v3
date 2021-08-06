package com.kyeeego.TFood.application.service;

import com.kyeeego.TFood.application.port.ActivityFindService;
import com.kyeeego.TFood.application.port.ActivityService;
import com.kyeeego.TFood.application.port.CalculationService;
import com.kyeeego.TFood.application.port.DayService;
import com.kyeeego.TFood.application.repository.UserRepository;
import com.kyeeego.TFood.domain.dto.activity.AddActivityDto;
import com.kyeeego.TFood.domain.dto.activity.AddProductDto;
import com.kyeeego.TFood.domain.models.Activity;
import com.kyeeego.TFood.domain.models.Day;
import com.kyeeego.TFood.domain.models.Product;
import com.kyeeego.TFood.domain.models.User;
import com.kyeeego.TFood.domain.types.Eating;
import com.kyeeego.TFood.domain.types.PFC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final DayService dayService;
    private final UserRepository userRepository;
    private final ActivityFindService activityFindService;
    private final CalculationService calculationService;

    @Override
    public Day addWater(Principal principal, int amount) {
        Day today = dayService.today(principal);
        today.setWater( today.getWater() + amount );
        dayService.update(today);
        return today;
    }

    @Override
    public Day addProduct(Principal principal, AddProductDto addFoodDto) {
        Day today = dayService.today(principal);
        float k = addFoodDto.getMass() / 100f;
        Product product = activityFindService.findProductById(addFoodDto.getProductId());

        today.setVitamins( today.getVitamins().add(product.getVitamins(), k) );
        today.setMinerals( today.getMinerals().add(product.getMinerals(), k) );

        today.addEating(addFoodDto.getEating(), product, k);

        dayService.update(today);
        return today;
    }

    @Override
    public Day setSleepTime(Principal principal, int duration) {
        Day today = dayService.today(principal);
        User user = userRepository.findByEmail(principal.getName()).get();

        today.setSleepTime(duration);

        double energyNeed = calculationService.dailyEnergyNeed(user, duration);

        double waterNeed = calculationService.waterNeed(user, energyNeed);
        PFC pfc = calculationService.dailyMacronutrientsNeed(energyNeed);

        today.setCarbsNeed((float) pfc.getCarbs());
        today.setFatsNeed((float) pfc.getFats());
        today.setProtsNeed((float) pfc.getProts());
        today.setWaterNeed((int) waterNeed);
        today.setKcalNeed((float) energyNeed);

        dayService.update(today);
        return today;
    }

    @Override
    public Day addActivity(Principal principal, AddActivityDto addActivityDto) {
        Day today = dayService.today(principal);
        User user = userRepository.findByEmail(principal.getName()).get();

        Activity activity = activityFindService
                .findActivityById(addActivityDto.getActivityId());


        double energyNeed = calculationService.sportEnergyNeed(user, addActivityDto.getLength(), activity.getEcost());

        today.addActivity(activity, addActivityDto.getLength(), energyNeed);
        today.setKcal((float) (today.getKcal() - energyNeed));

        dayService.update(today);

        return today;
    }

    @Override
    public void deleteProduct(Principal principal, Eating eating, int id) {
        Day today = dayService.today(principal);

        today.removeProduct(eating, id);

        dayService.update(today);
    }

    @Override
    public void deleteActivity(Principal principal, int id) {
        Day today = dayService.today(principal);

        today.removeActivity(id);

        dayService.update(today);
    }
}
