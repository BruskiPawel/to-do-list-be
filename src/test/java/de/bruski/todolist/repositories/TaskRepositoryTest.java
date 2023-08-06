package de.bruski.todolist.repositories;

import de.bruski.todolist.entities.Role;
import de.bruski.todolist.entities.Task;
import de.bruski.todolist.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveTaskTest() {
        // Arrange
        User user = User.builder()
                .username("steve")
                .password("pass123")
                .build();

        Task task = Task.builder()
                .content("Task with user role")
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build();
        // Act
        User savedUser = userRepository.save(user);
        task.setUser(savedUser);
        Task savedTask = taskRepository.save(task);

        // Assert
        Assertions.assertThat(savedTask).isNotNull();
        Assertions.assertThat(savedTask.getUser()).isEqualTo(savedUser);

    }

    @Test
    public void getTasksByUserTest() {
        // Arrange
        User user = User.builder()
                .username("steve")
                .password("pass123")
                .email("steve@gmail.com")
                .build();

        Task task = Task.builder()
                .content("Task with user role")
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build();
        // Act
        User savedUser = userRepository.save(user);
        task.setUser(savedUser);
        Task savedTask = taskRepository.save(task);
        Optional<Task> taskById = taskRepository.getTaskByUserAndId(savedUser, savedTask.getId());
        // Assert
        Assertions.assertThat(taskById).isNotEmpty();
        //Assertions.assertThat(taskById.get().getId()).isEqualTo(savedTask.getId());
        Assertions.assertThat(taskById.get().getUser()).isEqualTo(savedUser);
    }

    @Test
    public void deleteTaskByUserAndById() {
        // Arrange
        User user = User.builder()
                .username("steve")
                .password("pass123")
                .email("steve@gmail.com")
                .build();

        Task task = Task.builder()
                .content("Task with user role")
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build();
        // Act
        User savedUser = userRepository.save(user);
        task.setUser(savedUser);
        Task savedTask = taskRepository.save(task);
        Optional<Task> deletedTask = taskRepository.deleteTaskByUserAndId(savedUser, savedTask.getId());
        List<Task> result = taskRepository.getTasksByUser(savedUser);
        // Assert
        Assertions.assertThat(deletedTask).isNotEmpty();
        Assertions.assertThat(result).hasSize(0);

    }
}