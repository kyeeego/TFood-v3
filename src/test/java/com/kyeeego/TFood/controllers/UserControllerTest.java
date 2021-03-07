package com.kyeeego.TFood.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyeeego.TFood.users.UserController;
import com.kyeeego.TFood.users.UserService;
import com.kyeeego.TFood.users.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void findById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/60052020fa56bc35005b33fd");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        User user = new ObjectMapper().readValue(json, User.class);

        assertEquals("nest4@gmail.com", user.getEmail());
    }
}
