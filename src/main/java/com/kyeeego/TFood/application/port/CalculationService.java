package com.kyeeego.TFood.application.port;

import com.kyeeego.TFood.domain.models.User;
import com.kyeeego.TFood.domain.types.PFC;
import com.kyeeego.TFood.domain.types.WeightResult;

public interface CalculationService {
    PFC dailyMacronutrientsNeed(double DEN);
    WeightResult weightHeightRelation(int weight, int height, boolean gender);
    double waterNeed(User user, double DEN);
    double sleepEnergyNeed(User user, double duration);
    double sportEnergyNeed(User user, double duration, double energeticCost);
    double dailyEnergyNeed(User user, double sleepDuration);
}
