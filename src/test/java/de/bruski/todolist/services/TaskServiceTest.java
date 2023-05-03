package de.bruski.todolist.services;

import de.bruski.todolist.models.Task;
import de.bruski.todolist.repositories.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceTest {


    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    @BeforeEach
    void beforeEach() {
        Task task1 = new Task();
        task1.setDate(LocalDate.now().atStartOfDay());
        task1.setContent("First task");
        this.taskRepository.save(task1);

        Task task2 = new Task();
        task1.setDate(LocalDate.now().atStartOfDay());
        task1.setContent("Second task");
        this.taskRepository.save(task2);
    }
    @AfterEach
    void afterEach() {
        this.taskRepository.deleteAll();
    }

    @Test
    void getAllTask() {
        //given
        Iterable <Task> allTask = taskService.getAllTasks();
        //when
        //then
        Assertions.assertThat(allTask)
                .isNotEmpty()
                .isNotNull()
                .hasSize(2);
    }

    @Test
    void createNewTask() {
        //given
        Task taskToAdd = new Task();
        taskToAdd.setDate(LocalDateTime.now());
        taskToAdd.setContent("TEST TASK!");
        //when
        Task expectedTask = taskService.createNewTask(taskToAdd);
        Iterable<Task> allTasks = taskService.getAllTasks();
        //then
        Assertions.assertThat(allTasks)
                .isNotEmpty()
                .isNotNull()
                .hasSize(3)
                .contains(expectedTask);
    }

    @Test
    void deleteTaskById() {

        //given
        List <Task> allTasks = (List)taskService.getAllTasks();
        Task taskToDelete = allTasks.get(1);
        Long id = taskToDelete.getId();
        //when
        taskService.deleteTask(id);
        List <Task> allTaskAfterDeletion = (List)taskService.getAllTasks();
        //then
       Assertions.assertThat(allTaskAfterDeletion)
               .isNotNull()
               .isNotEmpty()
               .hasSize(1)
               .doesNotContain(taskToDelete);
    }

    @Test
    void deleteAllTasks() {
        // given
        taskService.deleteAllTasks();
        // when
        Iterable<Task> allTasks = taskService.getAllTasks();
        // then
        Assertions.assertThat(allTasks)
                .isEmpty();

    }
}