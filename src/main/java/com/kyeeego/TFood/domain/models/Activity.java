package com.kyeeego.TFood.domain.models;

import com.kyeeego.TFood.domain.types.ActivityType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "activities")
public class Activity {

    @Id
    private String id;

    private float ecost;
    private String name;

    private ActivityType type;

}
