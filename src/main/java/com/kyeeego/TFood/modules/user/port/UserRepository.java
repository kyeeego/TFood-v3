package com.kyeeego.TFood.modules.user.port;

import com.kyeeego.TFood.modules.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    public Optional<User> findByEmail(String email);
}

