package com.example.shop.controller;

import com.example.shop.database.entity.User;
import com.example.shop.exception.ResourceNotFoundException;
import com.example.shop.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getById_shouldReturn200WhenUserExists() throws Exception {
        User existingUser = new User("Robert", 300.00);
        ReflectionTestUtils.setField(existingUser, "id", 1L);

        when(userService.findById(1L)).thenReturn(existingUser);

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("Robert"))
                .andExpect(jsonPath("$.balance").value(300.00));

        verify(userService).findById(1L);
    }

    @Test
    void getById_shouldReturn404WhenUserDoesNotExist() throws Exception {
        when(userService.findById(99L)).thenThrow(new ResourceNotFoundException("Could not found a user with this id"));

        mockMvc.perform(get("/user/99"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getAll_shouldReturn200WhenUsersExist() throws Exception {
        User userOne = new User("Robert", 300.00);
        User userTwo = new User("Bruce", 500.00);
        List<User> userList = Arrays.asList(userOne, userTwo);

        when(userService.findAll()).thenReturn(userList);

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].username").value("Robert"))
                .andExpect(jsonPath("$[1].username").value("Bruce"))
                .andExpect(jsonPath("$[0].balance").value(300.00))
                .andExpect(jsonPath("$[1].balance").value(500.00));

        verify(userService).findAll();
    }

    @Test
    void getAll_shouldReturn200AndEmptyListWhenUsersDoNotExist() throws Exception {
        when(userService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));

        verify(userService).findAll();
    }

    @Test
    void post_shouldReturn201WhenUserIsValid() throws Exception {
        User savedUser = new User("Robert", 300.00);
        ReflectionTestUtils.setField(savedUser, "id", 1L);

        when(userService.save(any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("Robert"))
                .andExpect(jsonPath("$.balance").value(300.00));

        verify(userService).save(any(User.class));
    }

    @ParameterizedTest
    @MethodSource("usersWithRequiredFieldsMissing")
    void post_shouldReturn400WhenRequiredFieldsAreMissing(User invalidUser) throws Exception {
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidUser)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    static Stream<User> usersWithRequiredFieldsMissing() {
        return Stream.of(new User(null, 300.00),
                new User("", 300.00),
                new User("    ", 300.00),
                new User("Robert", null)
        );
    }

    @Test
    void post_shouldReturn400WhenBalanceIsNegative() throws Exception {
        User userWithInvalidBalance = new User("Robert", -300.00);

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userWithInvalidBalance)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("balance: Balance cannot be negative."));
    }
}