package com.kyeeego.TFood.users;

public class UserUtils {

    public static class PFC {
        public double prots;
        public double fats;
        public double carbs;

        public PFC(double prots, double fats, double carbs) {
            this.prots = Math.round(prots);
            this.fats = Math.round(fats);
            this.carbs = Math.round(carbs);
        }
    }

}



