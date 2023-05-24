package de.bruski.todolist.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.bruski.todolist.models.UserDTO;
import de.bruski.todolist.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    List<UserDTO> expectedUserList = Arrays.asList(
            UserDTO.builder().username("williamrobinson").email("williamrobinson@example.com").password("HelloWorld!").build(),
            UserDTO.builder().username("jacobmartin").email("jacobmartin@example.com").password("P@ssw0rd123!").build()
    );

    @Test
    void shouldAddNewUserToDataBase() throws Exception {
        UserDTO exceptedUser = new UserDTO(UUID.randomUUID(), "TestName", "testemail@test.com", "test123");

        given(userService.addNewUser(any(UserDTO.class))).willReturn(exceptedUser);

        mockMvc.perform(post("/new_user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(exceptedUser)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldLoginUser() throws Exception {
        UserDTO exceptedUser = new UserDTO(UUID.randomUUID(), "TestName", "testemail@test.com", "test123");
       ResponseEntity<String> responseEntity = ResponseEntity.ok().body("User logged in successfully.");
        //when(userService.loginUser(any(User.class))).thenReturn(ResponseEntity.ok().body("User logged in successfully."));

        doReturn(responseEntity).when(userService).loginUser(any(UserDTO.class));

        mockMvc.perform(post("/login_user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(exceptedUser)))
                .andExpect(status().isOk())
                .andExpect(content().string("User logged in successfully."));
    }

}