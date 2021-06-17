package com.kyeeego.TFood.application.repository;

import com.kyeeego.TFood.domain.models.Activity;
import com.kyeeego.TFood.domain.types.ActivityType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ActivityRepository extends MongoRepository<Activity, String> {
    List<Activity> findByTypeAndNameLike(ActivityType type, String q);
}
