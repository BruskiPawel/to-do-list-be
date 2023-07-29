package de.bruski.todolist.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.bruski.todolist.entities.Task;
import de.bruski.todolist.entities.User;
import de.bruski.todolist.models.TaskDTO;
import de.bruski.todolist.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(controllers = TaskController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllTasks() throws Exception {
        UUID userId = UUID.randomUUID();

        List<Task> listOfTasks = List.of(
                Task.builder()
                        .content("test task")
                        .id(UUID.randomUUID())
                        .user(User.builder()
                                .username("user1")
                                .password("password1")
                                .id(userId).build())
                        .build(),
                Task.builder()
                        .content("test task2")
                        .id(UUID.randomUUID())
                        .user(User.builder()
                                .username("user2")
                                .password("password2")
                                .id(userId).build())
                        .build()
        );

        List<TaskDTO> listOfTaskDtos = List.of(
                TaskDTO.builder()
                        .content("test task")
                        .id(listOfTasks.get(0).getId())
                        .build(),
                TaskDTO.builder()
                        .content("test task2")
                        .id(listOfTasks.get(1).getId())
                        .build()
        );

        given(taskService.getAllTasksByUser(ArgumentMatchers.any())).willReturn(listOfTaskDtos);

        ResultActions response = mockMvc.perform(get("/api/tasks/{id}".toString(), UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(listOfTaskDtos)));
    }

    @Test
    public void addNewTask() throws Exception {

        UUID userId = UUID.randomUUID();

                Task task = Task.builder()
                        .content("test task")
                        .id(UUID.randomUUID())
                        .user(User.builder()
                                .username("user1")
                                .password("password1")
                                .id(userId).build())
                        .build();

               TaskDTO taskDto =  TaskDTO.builder()
                        .content("test task")
                        .id(task.getId())
                        .build();

        given(taskService.createNewTask(ArgumentMatchers.any())).willReturn(taskDto);

        ResultActions response = mockMvc.perform(post("/add-new-task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto))
        );

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteTask() throws Exception {
        UUID userId = UUID.randomUUID();

        Task task = Task.builder()
                .content("test task")
                .id(UUID.randomUUID())
                .user(User.builder()
                        .username("user1")
                        .password("password1")
                        .id(userId).build())
                .build();

        TaskDTO taskDto =  TaskDTO.builder()
                .content("test task")
                .id(task.getId())
                .build();

        given(taskService.deleteTask(ArgumentMatchers.any())).willReturn(Optional.of(taskDto));

        ResultActions resonse = mockMvc.perform(delete("/delete-task/{id}", UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto)));
    }


}