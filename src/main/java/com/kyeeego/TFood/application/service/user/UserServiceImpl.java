package com.kyeeego.TFood.application.service.user;

import com.kyeeego.TFood.application.port.CalculationService;
import com.kyeeego.TFood.application.port.DayService;
import com.kyeeego.TFood.application.port.UserService;
import com.kyeeego.TFood.application.repository.UserRepository;
import com.kyeeego.TFood.domain.dto.user.UserCreateDto;
import com.kyeeego.TFood.domain.dto.user.UserResponse;
import com.kyeeego.TFood.domain.dto.user.UserUpdateDto;
import com.kyeeego.TFood.domain.dto.user.UserUpdateResponse;
import com.kyeeego.TFood.domain.models.Day;
import com.kyeeego.TFood.domain.models.User;
import com.kyeeego.TFood.domain.types.PFC;
import com.kyeeego.TFood.domain.types.WeightResult;
import com.kyeeego.TFood.exception.AlreadyExistsException;
import com.kyeeego.TFood.exception.BadRequestException;
import com.kyeeego.TFood.exception.NotFoundException;
import com.kyeeego.TFood.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CalculationService calculationService;
    private final DayService dayService;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("User already exists"));
    }

    @Override
    public User create(UserCreateDto userInput) {
        User user = new User(userInput);

        if (userRepository
                .findByEmail(user.getEmail())
                .isPresent()) {
            throw new AlreadyExistsException("User already exists");
        }


        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        userRepository.save(user);

        return user;
    }

    @Override
    @SneakyThrows
    public UserUpdateResponse update(Principal principal, UserUpdateDto userUpdateDto) {
        User user = userRepository.findByEmail(principal.getName()).get();

        BeanUtilsBean bub = new NullAwareBeanUtilsBean();
        bub.copyProperties(user, userUpdateDto);

        userRepository.save(user);

        Day day = dayService.today(principal);

        double DEN = calculationService.dailyEnergyNeed(user, day.getSleepTime());
        double waterNeed = calculationService.waterNeed(user, DEN);
        PFC pfcNeed = calculationService.dailyMacronutrientsNeed(DEN);

        day.setKcalNeed((float) DEN);
        day.setWaterNeed((int) waterNeed);
        day.setCarbsNeed((float) pfcNeed.getCarbs());
        day.setFatsNeed((float) pfcNeed.getFats());
        day.setProtsNeed((float) pfcNeed.getProts());

        dayService.update(day);

        WeightResult weightResult = calculationService.weightHeightRelation(user.getWeight(), user.getHeight(), user.isGender());

        return new UserUpdateResponse(
                weightResult,
                new UserResponse(user)
        );
    }
}
