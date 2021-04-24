package com.kyeeego.TFood.modules.day.port;

import com.kyeeego.TFood.modules.day.entity.Day;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DayRepository extends MongoRepository<Day, String> {
    Optional<Day> findByUserAndDate(String user, LocalDate date);
}
