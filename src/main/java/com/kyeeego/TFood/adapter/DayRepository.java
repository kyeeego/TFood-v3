package com.kyeeego.TFood.adapter;

import com.kyeeego.TFood.domain.Day;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DayRepository extends MongoRepository<Day, String> {
    Optional<Day> findByUserAndDate(String user, LocalDate date);
}
