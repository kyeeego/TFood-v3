package com.kyeeego.TFood.users;

import com.kyeeego.TFood.exceptions.global.RecordNotFoundException;
import com.kyeeego.TFood.users.models.User;
import com.kyeeego.TFood.users.models.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


//@EnableMongoRepositories(basePackages = "com.kyeeego.TFood.users")
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> find() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public User findByID(String id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new RecordNotFoundException();

        return user.get();
    }

}
