package com.kyeeego.TFood.domain.dto.user;

import lombok.Data;

@Data
public class UserUpdateDto {
    private String username;
    private String birthdate;
    private Integer weight;
    private Integer height;
    private Integer chest;
    private Boolean gender;
}
