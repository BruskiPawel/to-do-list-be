package de.bruski.todolist.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import de.bruski.todolist.models.Task;
import de.bruski.todolist.repositories.TaskRepository;
import de.bruski.todolist.services.TaskService;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.util.Streams.stream;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @MockBean
    private TaskService taskService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    List<Task> tasks = new ArrayList<>();

    @BeforeEach
    void setListOfTasks() {
        Task task = new Task();

        task.setDate(LocalDateTime.now());
        task.setContent("test 1");
        task.setId(100L);
        tasks.add(task);

        task.setDate(LocalDateTime.now());
        task.setContent("test 2");
        task.setId(102L);
        tasks.add(task);
    }

    @Test
    void testGetAllTasks() throws Exception {
        given(taskService.getAllTasks()).willReturn(tasks);

        Iterable<Task> all = taskService.getAllTasks();

        mockMvc.perform(get("/tasks")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(stream(all).toList().size())));
    }

    @Test
    void testGetTaskById() throws Exception {
        given(taskService.getAllTasks()).willReturn(tasks);
        Task task = stream(taskService.getAllTasks()).toList().get(0);

        given(taskService.getTaskById(task.getId())).willReturn(Optional.of(task));

        mockMvc.perform(get("/tasks/" + task.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(task.getId().intValue())))
                .andExpect(jsonPath("$.content", is(task.getContent())));
    }

    @Test
    void testAddNewTask() throws Exception {
        given(taskService.getAllTasks()).willReturn(tasks);

        given(taskService.createNewTask(any(Task.class))).willReturn(tasks.get(1));


        mockMvc.perform(post("/add-new-task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tasks.get(1))))
                .andExpect(status().isCreated());
        //      .andExpect(header().exists("Location");
    }
}