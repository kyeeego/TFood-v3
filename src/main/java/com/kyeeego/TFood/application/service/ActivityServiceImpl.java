package com.kyeeego.TFood.application.service;

import com.kyeeego.TFood.adapter.repository.ActivityRepository;
import com.kyeeego.TFood.adapter.repository.ProductRepository;
import com.kyeeego.TFood.application.port.ActivityService;
import com.kyeeego.TFood.domain.models.Activity;
import com.kyeeego.TFood.domain.models.Product;
import com.kyeeego.TFood.domain.types.ActivityType;
import com.kyeeego.TFood.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final ProductRepository productRepository;

    @Override
    public List<Activity> findAllActivitiesWithQuery(ActivityType type, String q) {
        return activityRepository.findByTypeAndNameLike(type, q);
    }

    @Override
    public Activity findActivityById(String id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No activity with such id"));
    }

    @Override
    public List<Product> findProductsByQuery(String q) {
        return productRepository.findProductsByNameLikeOrCategoryLike(q, q);
    }

    @Override
    public Product findProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found by id"));
    }
}