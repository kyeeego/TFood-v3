package com.kyeeego.TFood.domain.dto.activity;

import com.kyeeego.TFood.domain.types.Eating;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddProductDto {
    @NotNull
    private String productId;

    @NotNull
    private Eating eating;

    @NotNull
    private int mass;
}
