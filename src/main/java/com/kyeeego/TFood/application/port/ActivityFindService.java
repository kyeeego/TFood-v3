package com.kyeeego.TFood.application.port;

import com.kyeeego.TFood.domain.models.Activity;
import com.kyeeego.TFood.domain.models.Product;
import com.kyeeego.TFood.domain.types.ActivityType;

import java.util.List;

public interface ActivityFindService {
    List<Activity> findAllActivitiesWithQuery(ActivityType type, String q);
    Activity findActivityById(String id);
    List<Product> findProductsByQuery(String q);
    Product findProductById(String id);
}
