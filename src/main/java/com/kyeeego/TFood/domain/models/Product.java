package com.kyeeego.TFood.domain.models;

import com.kyeeego.TFood.domain.types.Minerals;
import com.kyeeego.TFood.domain.types.Vitamins;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private Vitamins vitamins;
    private Minerals minerals;

    private String name;
    private String category;

    private float prots;
    private float fats;
    private float carbs;
    private float alimentaryFiber;
    private float kcal;

}
