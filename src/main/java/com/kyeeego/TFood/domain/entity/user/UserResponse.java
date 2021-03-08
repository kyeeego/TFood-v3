package com.kyeeego.TFood.domain.entity.user;

import com.kyeeego.TFood.domain.entity.user.User;

public class UserResponse {
    public String id;

    public String username;
    public String birthdate;

    public int chest;
    public int weight;
    public int height;
    public boolean gender;

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
