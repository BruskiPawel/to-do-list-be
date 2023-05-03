package de.bruski.todolist.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.bruski.todolist.models.Task;
import de.bruski.todolist.models.User;
import de.bruski.todolist.repositories.UserRepository;
import de.bruski.todolist.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    MockMvc mockMvc;
    @BeforeEach
    void beforeEach() {
        User user = new User();
        user.setUsername("Kamil");
        user.setPassword("test123");
        userService.addNewUser(user);

        user.setUsername("Pawel");
        user.setPassword("123test");
        userService.addNewUser(user);
    }

    @AfterEach
    void afterEach() {

    }


    @Test
    void addNewUserTest() throws Exception {

        User user = new User();
        user.setUsername("Anna");
        user.setPassword("ania123");

        userRepository.deleteAll();
        userRepository.save(user);
//        given(userRepository.save(user)).willReturn(userRepository.findAll().get(0));

        mockMvc.perform(post("/new_user")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void loginUserTest() {

    }
}