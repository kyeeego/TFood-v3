package com.kyeeego.TFood.domain.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "weight_height_relations")
public class WHR {

    @Id
    private String id;

    private double perfect;
    private double too_little;
    private double too_much;

    private boolean gender;
    private int height;

}
