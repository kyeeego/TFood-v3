package com.kyeeego.TFood.application.controller;

import com.kyeeego.TFood.application.port.UserService;
import com.kyeeego.TFood.domain.dto.user.UserUpdateDto;
import com.kyeeego.TFood.domain.dto.user.UserUpdateResponse;
import com.kyeeego.TFood.domain.models.User;
import com.kyeeego.TFood.domain.dto.user.UserCreateDto;
import com.kyeeego.TFood.domain.dto.user.UserResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping
//    public List<UserResponse> findAll() {
//        return userService.findAll()
//                .stream()
//                .map(UserResponse::new)
//                .collect(Collectors.toList());
//    }

    @ApiOperation("Найти пользователя по ID")
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable("id") String id) {
        return new UserResponse(userService.findById(id));
    }

    @ApiOperation("Зарегистрировать пользователя")
    @PostMapping
    public UserResponse create(@Valid @RequestBody UserCreateDto userCreateDto) {
        User u = userService.create(userCreateDto);
        log.info("User " + u.getId() + " has successfully registered email " + u.getEmail());
        return new UserResponse(u);
    }

    @ApiOperation("Обновить любые параметры пользователя (из возможных). Не требуемые значения оставлять null")
    @PostMapping("/update")
    public UserUpdateResponse update(@RequestBody UserUpdateDto userUpdateDto, Principal principal) {
        return userService.update(principal, userUpdateDto);
    }

}
