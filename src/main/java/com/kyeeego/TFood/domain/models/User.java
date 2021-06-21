package com.kyeeego.TFood.domain.models;

import com.kyeeego.TFood.domain.dto.user.UserCreateDto;
import com.kyeeego.TFood.domain.types.PFC;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

@Document(collection = "users")
@Data
public class User {

    @Id
    private String id;

    private String username;
    private String email;
    private String password;
    private String birthdate;

    private int gmttimezone;
    private int chest;
    private int weight;
    private int height;
    private boolean gender;

    public User() {
    }

    public User(UserCreateDto user) {
        this.birthdate = user.getBirthdate();
        this.username = user.getUsername();
        this.chest = user.getChest();
        this.weight = user.getWeight();
        this.height = user.getHeight();
        this.gender = user.isGender();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.gmttimezone = user.getGmttimezone();
    }

    @Override
    public String toString() {
        return "User[" + "id='" + id + '\'' + ", username='" + username + '\'' + ", email='" + email + '\'' +
                ", password='" + password + '\'' + ", birthdate='" + birthdate + '\'' + ", chest=" + chest +
                ", weight=" + weight + ", height=" + height + ", gender=" + gender + ']';
    }
}