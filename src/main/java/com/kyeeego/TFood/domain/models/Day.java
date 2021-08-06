package com.kyeeego.TFood.domain.models;

import com.kyeeego.TFood.domain.types.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.Store;
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

    private List<StoredProduct> eating = new ArrayList<>();

    private List<StoredActivity> activity = new ArrayList<>();

    private Vitamins vitamins = new Vitamins();
    private Minerals minerals = new Minerals();

    public Day(String userEmail, LocalDate day) {
        user = userEmail;
        date = day;
    }

    public void addActivity(Activity activity, int duration, double kcal) {
        StoredActivity storedActivity = new StoredActivity();
        storedActivity.setActivity(activity);
        storedActivity.setDuration(duration);
        storedActivity.setKcal(kcal);
        storedActivity.setId(
                this.activity.size() != 0
                        ? this.activity.get(this.activity.size() - 1).getId() + 1
                        : 1
        );

        this.activity.add(storedActivity);
    }

    public void addEating(Eating eating, Product product, float k) {
        product.setProts(product.getProts() * k);
        product.setFats(product.getFats() * k);
        product.setCarbs(product.getCarbs() * k);
        product.setKcal(product.getKcal() * k);

        kcal += product.getKcal();

        StoredProduct storedProduct = new StoredProduct();
        storedProduct.setEating(eating);
        storedProduct.setProduct(product);
        storedProduct.setId(
                this.eating.size() != 0
                ? this.eating.get(this.eating.size() - 1).getId() + 1
                : 1
        );

        this.eating.add(storedProduct);
    }

    public void removeProduct(int id) {
        this.eating.removeIf((a) -> {
            if (a.getId() == id) {
                kcal -= a.getProduct().getKcal();
                return true;
            }

            return false;
        });
    }

    public void removeActivity(int id) {
        activity.removeIf((a) -> {
            if (a.getId() == id) {
                kcal -= a.getKcal();
                return true;
            }

            return false;
        });
    }

}
