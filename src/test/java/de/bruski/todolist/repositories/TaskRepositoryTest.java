package de.bruski.todolist.repositories;

import de.bruski.todolist.bootstrap.BootStrapData;
import de.bruski.todolist.entities.Task;
import de.bruski.todolist.services.TaskCsvService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    BootStrapData bootStrapData;

    @BeforeEach
    void beforeEach() throws FileNotFoundException {
//        List<Task> expectedListOfTask = getListOfTaskToTest();
//        taskRepository.saveAll(expectedListOfTask);
        bootStrapData.loadTaskCSVData();
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
        return Task.builder().date(date).content(splitedLine[2]).build();
    }

    @Test
    void getalltest() {
        List<Task> all = taskRepository.findAll();
        System.out.println(all.size());
    }
    @Test
    void shouldReturnAllTasksFromDataBase() {
        // given
        // when
        List<Task> result = taskRepository.findAll();
        // then
        Assertions.assertThat(taskRepository.count()).isEqualTo(32);
        Assertions.assertThat(result.get(4).getId()).isNotNull();
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
        Assertions.assertThat(result).isEqualTo(31);
        Assertions.assertThat(DeletedTask).isEmpty();
    }

    @Test
    void shouldFindTaskByContent() {
        // given
        String testTaskContent = "Prepare for upcoming presentation";
        // when
        Task result = taskRepository.findByContent(testTaskContent);
        // then
        Assertions.assertThat(result.getContent()).isEqualTo(testTaskContent);
    }

    @Test
    void shouldFindAllTaskByDate() {
        // given
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateForTest = LocalDate.parse("2023-01-16", dateFormatter);
        // when
        List<Task> result = taskRepository.findTasksByDate(dateForTest);
        // then
        Assertions.assertThat(result.size()).isNotNull().isEqualTo(3);
    }

    @Test
    void shouldSaveNewTaskInDataBase() {
        // given
        Task testTaskToSaveInDataBase = Task.builder().date(LocalDate.now()).time(LocalTime.now()).content("Test Task").build();
        // when
        taskRepository.save(testTaskToSaveInDataBase);
        Task testTask = taskRepository.findByContent("Test Task");
        // then
        Assertions.assertThat(taskRepository.count()).isEqualTo(33);
        Assertions.assertThat(testTask.getId()).isNotNull();
        Assertions.assertThat(testTask.getContent()).isEqualTo("Test Task");
    }


}