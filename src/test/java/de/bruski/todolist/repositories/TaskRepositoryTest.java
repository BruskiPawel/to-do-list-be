package de.bruski.todolist.repositories;

import de.bruski.todolist.bootstrap.BootStrapData;
import de.bruski.todolist.entities.Task;
import de.bruski.todolist.entities.User;
import de.bruski.todolist.services.TaskCsvService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@Import({BootStrapData.class, TaskCsvService.class})
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BootStrapData bootStrapData;
//    @BeforeEach
//    void beforeEach() throws FileNotFoundException {
////        List<Task> expectedListOfTask = getListOfTaskToTest();
////        taskRepository.saveAll(expectedListOfTask);
//        bootStrapData.loadTaskCSVData();
//    }
//
//    @AfterEach
//    void afterEach() {
//        taskRepository.deleteAll();
//    }
//
//    List<Task> getListOfTaskToTest() {
//        List<Task> listOfTestTasks = List.of();
//        File file = new File("src/test/resources/test_task_data");
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
//            listOfTestTasks = bufferedReader.lines().map(this::mapToTask).collect(Collectors.toList());
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return listOfTestTasks;
//    }
//
//    Task mapToTask(String line) {
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
//
//        String[] splitedLine = line.split(";");
//        LocalDate date = LocalDate.parse(splitedLine[0], dateFormatter);
//        return Task.builder().date(date).content(splitedLine[2]).build();
//    }


    @Test
    void checkOneToManyMap() throws Exception {
        // Given
        bootStrapData.run(); // Load test data from CSV files

        // When
        System.out.println(taskRepository.count());
        // Then
    }
    @Test
    void shouldReturnAllTasksFromDataBase() {
        // given
User user = userRepository.findAll().get(0);
        // when
        List<Task> result = taskRepository.findAllByUser(user);
        // then
        Assertions.assertThat(taskRepository.count()).isEqualTo(16);
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
        Assertions.assertThat(result).isEqualTo(15);
        Assertions.assertThat(DeletedTask).isEmpty();
    }

    @Test
    void shouldFindTaskByContent() {
        // given
        String testTaskContent = "Meet with marketing team";
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