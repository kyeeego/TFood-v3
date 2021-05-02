package com.kyeeego.TFood.domain.dto.user;

import com.kyeeego.TFood.domain.models.User;
import lombok.Data;

@Data
public class UserResponse {
    private String id;

    private String username;
    private String birthdate;

    private int chest;
    private int weight;
    private int height;
    private boolean gender;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.birthdate = user.getBirthdate();
        this.chest = user.getChest();
        this.weight = user.getWeight();
        this.height = user.getHeight();
        this.gender = user.isGender();
    }
}
