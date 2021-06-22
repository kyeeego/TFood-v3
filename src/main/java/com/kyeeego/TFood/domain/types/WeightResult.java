package com.kyeeego.TFood.domain.types;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeightResult {
    private WeightValue weightValue;
    private double recommendedWeight;
}
