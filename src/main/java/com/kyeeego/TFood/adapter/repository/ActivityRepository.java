package com.kyeeego.TFood.adapter.repository;

import com.kyeeego.TFood.domain.models.Activity;
import com.kyeeego.TFood.domain.types.ActivityType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends MongoRepository<Activity, String> {
    List<Activity> findByTypeAndNameLike(ActivityType type, String q);
}
