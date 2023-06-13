package de.bruski.todolist.controllers;

import de.bruski.todolist.entities.Task;
import de.bruski.todolist.entities.User;
import de.bruski.todolist.repositories.TaskRepository;
import de.bruski.todolist.repositories.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    @Test
    void findAllTasksByUser() {
        User expectedUser = userRepository.findAll().get(0);
        List<Task> expectedTasks = taskRepository.findAllByUser(expectedUser);

        Assertions.assertThat(expectedTasks.size()).isEqualTo(9);
        Assertions.assertThat(expectedUser).isNotNull();
    }





}