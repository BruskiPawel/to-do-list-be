package de.bruski.todolist.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.bruski.todolist.models.TaskDTO;
import de.bruski.todolist.services.TaskService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskService taskService;

    @Autowired
    ObjectMapper objectMapper;
    List<TaskDTO> expectedTaskList = Arrays.asList(
            new TaskDTO(UUID.randomUUID(), LocalDate.now(), LocalTime.now(), "Task 1"),
            new TaskDTO(UUID.randomUUID(), LocalDate.now(), LocalTime.now(), "Task 2")
    );

    @Test
    void shouldReturnAllTasks() throws Exception {

        given(taskService.getAllTasks(any(), any())).willReturn(expectedTaskList);

        mockMvc.perform(get("/tasks")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void shouldReturnTaskById() throws Exception {
        TaskDTO expectedTask = expectedTaskList.get(1);

        given(taskService.getTaskById(any(UUID.class))).willReturn(Optional.of(expectedTask));

        mockMvc.perform(get("/task-by-id/" + expectedTask.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(expectedTask.getId().toString())));
    }

    @Test
    void shouldDeleteTaskFromDataBase() throws Exception {
        TaskDTO taskToDelete = expectedTaskList.get(1);

        mockMvc.perform(delete("/delete-task/" + taskToDelete.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());


        ArgumentCaptor<UUID> uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);

        verify(taskService).deleteTask(uuidArgumentCaptor.capture());

        Assertions.assertThat(taskToDelete.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void shouldAddNewTaskToDataBase() throws Exception {
        TaskDTO newTask = new TaskDTO(UUID.randomUUID(), LocalDate.now(), LocalTime.now(), "New Task");

        given(taskService.createNewTask(any(TaskDTO.class))).willReturn(newTask);

        mockMvc.perform(post("/add-new-task")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isCreated());
    }
}