package com.kyeeego.TFood.domain.dto.activity;

import com.kyeeego.TFood.domain.types.ActivityType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddActivityDto {
    @NotNull
    private String activityId;

    @NotNull
    private int length;

    @NotNull
    private ActivityType type;
}
