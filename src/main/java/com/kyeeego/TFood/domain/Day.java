package com.kyeeego.TFood.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.Principal;
import java.time.LocalDate;

@Document(collection = "days")
@Data
@NoArgsConstructor
public class Day {

    @Id
    private String id;

    private int water;
    private float carbs;
    private float fats;
    private float prots;
    private float kcal;
    private float rating;
    // user's email, not ID
    private String user;
    private LocalDate date;

    public Day(String userEmail, LocalDate day) {
        water = 0;
        carbs = 0f;
        fats = 0f;
        prots = 0f;
        kcal = 0f;
        rating = 0f;
        user = userEmail;
        date = day;
    }

}
