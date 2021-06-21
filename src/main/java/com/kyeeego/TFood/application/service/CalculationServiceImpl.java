package com.kyeeego.TFood.application.service;

import com.kyeeego.TFood.application.port.CalculationService;
import com.kyeeego.TFood.domain.models.User;
import com.kyeeego.TFood.domain.types.PFC;
import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService {

    public PFC dailyMacronutrientsNeed(double DEN) {
        double prots = DEN * 0.12;
        double fats = DEN * 0.3;
        double carbs = DEN - (prots + fats);

        return new PFC(prots / 4, fats / 9, carbs / 4);
    }

    public double perfectBodyMass(User user) {
        int height = user.getHeight();
        int chest = user.getChest();

        int x1;
        if (height >= 140 && height <= 166)
            x1 = height - 100;
        else if (height >= 167 && height <= 175)
            x1 = height - 105;
        else if (height > 175)
            x1 = height - 110;
        else return 0;

        double x2 = (height + chest) / 240.0;
        double x3 = height - 100 - height / (user.isGender() ? 10.0 : 20.0);

        return (x1 + x2 + x3) / 3.0;
    }

    public double basalMetabolicRate(User user, boolean isPerfect) {
        int weight = user.getWeight();

        if (!isPerfect)
            return user.isGender()
                    ? 17.5 * weight + 651
                    : 12.2 * weight + 746;
        else {
            double mass = perfectBodyMass(user);
            return user.isGender()
                    ? 17.5 * mass + 651
                    : 12.2 * mass + 746;
        }
    }

    public double waterNeed(User user, double DEN) {
        return Math.round(DEN - thermogenesisEnergyNeed(user));
    }

    public double sleepEnergyNeed(User user, double duration) {
        return (duration + basalMetabolicRate(user,false)) / 24;
    }

    public double thermogenesisEnergyNeed(User user) {
        return basalMetabolicRate(user, false) / 10;
    }

    public double sportEnergyNeed(User user, double duration, double energeticCost) {
        return user.getWeight() * energeticCost * duration / 60 * basalMetabolicRate(user, false);
    }

    public double dailyEnergyNeed(User user, double sleepDuration) {

        double bmr = basalMetabolicRate(user,false);

        double sleep = sleepEnergyNeed(user, sleepDuration);
        double thermogenesis = thermogenesisEnergyNeed(user);
        double otherWork = 1.4 *
                (24 - sleepDuration)
                * bmr / 24;

        return Math.round(sleep + thermogenesis + otherWork);
    }

}
