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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    private List<User> users = new ArrayList<>();

    @Autowired
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

    given(userService.addNewUser(User.builder().eMail("pawel@gmail.com").build())).willReturn((userService.getAllUsers().get(0)));

        mockMvc.perform(post("/new_user")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void loginUserTest() {

    }
}