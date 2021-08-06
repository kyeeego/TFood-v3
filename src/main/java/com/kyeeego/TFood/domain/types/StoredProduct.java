package com.kyeeego.TFood.domain.types;

import com.kyeeego.TFood.domain.models.Product;
import lombok.Data;

@Data
public class StoredProduct {
    private Product product;
    private Eating eating;
    private int id;
}
