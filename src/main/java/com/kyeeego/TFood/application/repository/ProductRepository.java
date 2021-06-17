package com.kyeeego.TFood.application.repository;

import com.kyeeego.TFood.domain.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findProductsByNameIsContainingOrCategoryIsContaining(String name, String category);
}
