package de.bruski.todolist.repositories;

import de.bruski.todolist.entities.Task;
import de.bruski.todolist.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static net.bytebuddy.matcher.ElementMatchers.any;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;


    @BeforeEach
    void beforeEach() throws FileNotFoundException {
        List<Task> expectedListOfTask = getListOfTaskToTest();
        taskRepository.saveAll(expectedListOfTask);
    }

    @AfterEach
    void afterEach() {
        taskRepository.deleteAll();
    }

    List<Task> getListOfTaskToTest() {
        List<Task> listOfTestTasks = List.of();
        File file = new File("src/test/resources/test_task_data");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
            listOfTestTasks = bufferedReader.lines().map(this::mapToTask).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listOfTestTasks;
    }

    Task mapToTask(String line) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String[] splitedLine = line.split(";");
        LocalDate date = LocalDate.parse(splitedLine[0], dateFormatter);
        return Task.builder().date(date).content(splitedLine[2]).user(User.builder().username("user1").id(UUID.fromString("07a4d1b9-9ad3-4da4-8ff3-577a34ec6e26")).build()).build();
    }


    @Test
    void shouldReturnAllTasksFromDataBase() {
        // given
        User user = User.builder().username("user1").id(UUID.randomUUID()).build();
        // when
        List<Task> result = taskRepository.findAllByUser(user);
        // then
        Assertions.assertThat(taskRepository.count()).isEqualTo(0);
        Assertions.assertThat(user).isNotNull();
    }

    @Test
    void shouldDeleteTaskFromDataBase() {
        // given
        List<Task> taskFromDataBase = taskRepository.findAll();
        Task taskToDelete = taskFromDataBase.get(5);
        // given and when
        taskRepository.deleteById(taskToDelete.getId());
        long result = taskRepository.count();
        Optional<Task> DeletedTask = taskRepository.findById(taskToDelete.getId());
        // then
        Assertions.assertThat(result).isEqualTo(30);
        Assertions.assertThat(DeletedTask).isEmpty();
    }

    @Test
    void shouldFindTaskByContent() {
        // given
        String testTaskContent = "Research new marketing strategies";
        // when
        Task result = taskRepository.findByContent(testTaskContent);
        // then
        Assertions.assertThat(result.getContent()).isEqualTo(testTaskContent);
    }

    @Test
    void shouldFindAllTaskByDate() {
        // given
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateForTest = LocalDate.parse("2023-06-08", dateFormatter);
        // when
        List<Task> result = taskRepository.findTasksByDate(dateForTest);
        // then
        Assertions.assertThat(result.size()).isNotNull().isEqualTo(2);
    }

    @Test
    void shouldSaveNewTaskInDataBase() {
        // given
        Task testTaskToSaveInDataBase = Task.builder().date(LocalDate.now()).time(LocalTime.now()).content("Test Task").build();
        // when
        taskRepository.save(testTaskToSaveInDataBase);
        Task testTask = taskRepository.findByContent("Test Task");
        // then
        Assertions.assertThat(taskRepository.count()).isEqualTo(17);
        Assertions.assertThat(testTask.getId()).isNotNull();
        Assertions.assertThat(testTask.getContent()).isEqualTo("Test Task");
    }

    @Test
    void checkOneToManyMapping() {
        Task task = taskRepository.findAll().get(4);
        System.out.println(taskRepository.count());
    }


}