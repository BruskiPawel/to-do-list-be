package de.bruski.todolist.controllers;

import de.bruski.todolist.entities.Task;
import de.bruski.todolist.repositories.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;


@SpringBootTest
class TaskControllerTestIT {

    @Autowired
    TaskController taskController;

    @Autowired
    TaskRepository taskRepository;

    @Test
    void findAllTasks() {
        List<Task> expectedTasks = taskRepository.findAll();
        Assertions.assertThat(expectedTasks.size()).isEqualTo(16);
    }





}