package com.kyeeego.TFood.users;

import com.kyeeego.TFood.users.models.User;
import com.kyeeego.TFood.users.models.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.find();
    }

    @GetMapping("/{id}")
    public User findByID(@PathVariable("id") String id) {
        return userService.findByID(id);
    }

}
