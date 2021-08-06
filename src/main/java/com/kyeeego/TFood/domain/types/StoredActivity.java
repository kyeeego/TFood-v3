package com.kyeeego.TFood.domain.types;

import com.kyeeego.TFood.domain.models.Activity;
import lombok.Data;

@Data
public class StoredActivity {
    private Activity activity;
    private int duration;
    private double kcal;

    private int id;
}
