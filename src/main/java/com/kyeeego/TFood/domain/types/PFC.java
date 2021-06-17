package com.kyeeego.TFood.domain.types;

import lombok.Data;

@Data
public class PFC {
    public double prots;
    public double fats;
    public double carbs;

    public PFC(double prots, double fats, double carbs) {
        this.prots = Math.round(prots);
        this.fats = Math.round(fats);
        this.carbs = Math.round(carbs);
    }
}