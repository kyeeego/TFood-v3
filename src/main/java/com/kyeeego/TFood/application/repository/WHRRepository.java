package com.kyeeego.TFood.application.repository;

import com.kyeeego.TFood.domain.models.WHR;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WHRRepository extends MongoRepository<WHR, String> {
    Optional<WHR> findByGenderAndHeight(boolean gender, int height);
}
