package com.kyeeego.TFood.domain.dto.user;

import com.kyeeego.TFood.domain.types.WeightResult;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateResponse {
    private WeightResult weightResult;
    private UserResponse user;
}
