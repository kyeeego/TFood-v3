package com.kyeeego.TFood.application.service;

import com.kyeeego.TFood.application.port.DayService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DayServiceImplTest {

    @Autowired
    private DayService service;

    private Principal user;

    // придется опять трахаться со spring security...
    @BeforeAll
    private void setUp() {

    }

    @Test
    private void pastWeek() {
        service.pastWeek();
    }
}