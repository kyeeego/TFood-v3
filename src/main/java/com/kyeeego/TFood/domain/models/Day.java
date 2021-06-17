package com.kyeeego.TFood.domain.models;

import com.kyeeego.TFood.domain.types.Eating;
import com.kyeeego.TFood.domain.types.Minerals;
import com.kyeeego.TFood.domain.types.Vitamins;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "days")
@Data
@NoArgsConstructor
public class Day {

    @Id
    private String id;

    private int water;
    private int waterNeed;

    private float carbs;
    private float carbsNeed;

    private float fats;
    private float fatsNeed;

    private float prots;
    private float protsNeed;

    private float kcal;
    private float kcalNeed;

    private float rating;
    private float sleepTime;

    // user's email, not ID
    private String user;
    private LocalDate date;

    private List<Product> breakfast;
    private List<Product> lunch;
    private List<Product> dinner;
    private List<Product> snack;

    private Vitamins vitamins;
    private Minerals minerals;

    public Day(String userEmail, LocalDate day) {
        water = 0; waterNeed = 0;
        carbs = 0f; carbsNeed = 0f;
        fats = 0f; fatsNeed = 0f;
        prots = 0f; protsNeed = 0f;
        kcal = 0f; kcalNeed = 0f;
        rating = 0f;
        sleepTime = 0f;
        vitamins = new Vitamins();
        minerals = new Minerals();
        breakfast = new ArrayList<>();
        lunch = new ArrayList<>();
        dinner = new ArrayList<>();
        snack = new ArrayList<>();
        user = userEmail;
        date = day;
    }

    public void addEating(Eating eating, Product product, float k) {
        product.setProts( product.getProts() * k );
        product.setFats( product.getFats() * k );
        product.setCarbs( product.getCarbs() * k );
        product.setKcal( product.getKcal() * k );

        kcal += product.getKcal();

        switch (eating) {
            case BREAKFAST:
                breakfast.add(product); break;
            case SNACK:
                snack.add(product); break;
            case LUNCH:
                lunch.add(product); break;
            case DINNER:
                dinner.add(product); break;
        }
    }

}
