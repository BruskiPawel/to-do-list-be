package de.bruski.todolist.controllers;

import de.bruski.todolist.models.Task;
import de.bruski.todolist.models.User;
import de.bruski.todolist.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.web.servlet.function.RequestPredicates.POST;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;


    List<User> expectedUserList = List.of();

    @BeforeEach
    void beforeEach() {
        expectedUserList = Arrays.asList(
                User.builder().username("williamrobinson").email("williamrobinson@example.com").password("HelloWorld!").build(),
                User.builder().username("jacobmartin").email("jacobmartin@example.com").password("P@ssw0rd123!").build()
        );
    }

//    @Test
//    void shouldGetAllUsersFromDataBase() throws Exception {
//
//        mockMvc.perform(post("/new_user")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect()
//                .andExpect((ResultMatcher) ResponseEntity.ok());
//
//    }

}