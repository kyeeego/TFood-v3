package com.kyeeego.TFood.domain.entity.user;

import com.kyeeego.TFood.domain.entity.user.dto.UserCreateDto;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

@Document(collection = "users")
@Data
public class User {

    @Id
    private String id;

    private String username;
    private String email;
    private String password;
    private String birthdate;

    private int chest;
    private int weight;
    private int height;
    private boolean gender;

    private String accessToken;
    private String refreshToken;

    public User() {
    }

    public User(UserCreateDto user) {
        this.birthdate = user.getBirthdate();
        this.username = user.getUsername();
        this.chest = user.getChest();
        this.weight = user.getWeight();
        this.height = user.getHeight();
        this.gender = user.isGender();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    @Override
    public String toString() {
        return "User[" + "id='" + id + '\'' + ", username='" + username + '\'' + ", email='" + email + '\'' +
                ", password='" + password + '\'' + ", birthdate='" + birthdate + '\'' + ", chest=" + chest +
                ", weight=" + weight + ", height=" + height + ", gender=" + gender + ']';
    }

    // Calculating physical parameters

    public PFC dailyMacronutrientsNeed(double DEN) {
        double prots = DEN * 0.12;
        double fats = DEN * 0.3;
        double carbs = DEN - (prots + fats);

        return new PFC(prots / 4, fats / 9, carbs / 4);
    }

    private double perfectBodyMass() {
        int x1;
        if (height >= 140 && height <= 166)
            x1 = height - 100;
        else if (height >= 167 && height <= 175)
            x1 = height - 105;
        else if (height > 175)
            x1 = height - 110;
        else return 0;

        double x2 = (height + chest) / 240.0;
        double x3 = height - 100 - height / (gender ? 10.0 : 20.0);

        return (x1 + x2 + x3) / 3.0;
    }

    private double basalMetabolicRate(boolean isPerfect) {
        if (!isPerfect)
            return gender
                    ? 17.5 * weight + 651
                    : 12.2 * weight + 746;
        else {
            double mass = perfectBodyMass();
            return gender
                    ? 17.5 * mass + 651
                    : 12.2 * mass + 746;
        }
    }

    public double waterNeed(double DEN) {
        double bmr = basalMetabolicRate(false);


        return Math.round(DEN - thermogenesisEnergyNeed());
    }

    public double sleepEnergyNeed(double duration) {
        return (duration + basalMetabolicRate(false)) / 24;
    }

    public double houseworkEnergyNeed(double duration) {
        return 1.8 * sleepEnergyNeed(duration);
    }

    public double thermogenesisEnergyNeed() {
        return basalMetabolicRate(false) / 10;
    }

    public double sportEnergyNeed(double duration, double energeticCost) {
        return weight * energeticCost * duration / 60 * basalMetabolicRate(false);
    }

    public double dailyEnergyNeed(double sleepDuration,
                                  double houseworkDuration,
                                  double sportDuration,
                                  double sportEnergeticCost) {

        double bmr = basalMetabolicRate(false);

        double sleep = sleepEnergyNeed(sleepDuration);
        double housework = houseworkEnergyNeed(houseworkDuration);
        double thermogenesis = thermogenesisEnergyNeed();
        double sport = sportEnergyNeed(sportDuration, sportEnergeticCost);
        double otherWork = 1.4 *
                (24 - sleepDuration - sportDuration - houseworkDuration)
                * bmr / 24;

        return Math.round(sleep + housework + thermogenesis + sport + otherWork);
    }
}

class PFC {
    public double prots;
    public double fats;
    public double carbs;

    public PFC(double prots, double fats, double carbs) {
        this.prots = Math.round(prots);
        this.fats = Math.round(fats);
        this.carbs = Math.round(carbs);
    }
}
