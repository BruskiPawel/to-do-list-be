package de.bruski.todolist.services;

import de.bruski.todolist.entities.Task;
import de.bruski.todolist.entities.User;
import de.bruski.todolist.mappers.TaskMapper;
import de.bruski.todolist.models.TaskDTO;
import de.bruski.todolist.repositories.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskMapper taskMapper;

    @Test
    public void createNewTaskTest() {

        Task task = Task.builder()
                .content("test task")
                .id(UUID.randomUUID())
                .user(User.builder()
                        .username("user1")
                        .password("password1")
                        .id(UUID.randomUUID()).build())
                .build();

        TaskDTO taskDto = TaskDTO.builder()
                .content("test task")
                .id(task.getId())
                .build();

    when(taskRepository.save(any(Task.class))).thenReturn(task);

    when(taskMapper.taskDtoToTask(any(TaskDTO.class))).thenReturn(task);
    when(taskMapper.taskToTaskDto(any(Task.class))).thenReturn(taskDto);

    TaskDTO savedTask = taskService.createNewTask(taskDto);
        UUID id = task.getId();

        Assertions.assertThat(savedTask).isNotNull();
        Assertions.assertThat(savedTask.getId()).isEqualTo(id);
        Assertions.assertThat(taskRepository.findAllByUserId(task.getUser().getId())).isNotNull();
    }


    @Test
    public void GetAllTaskByUserTest() {
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

        when(taskRepository.findAllByUserId(userId)).thenReturn(listOfTasks);

        when(taskMapper.taskListToTaskDtoList(listOfTasks)).thenReturn(listOfTaskDtos);

        List<TaskDTO> allTasks = taskService.getAllTasksByUser(userId);

        Assertions.assertThat(allTasks).isNotNull().hasSize(2);
    }



    @Test
    public void deleteTaskTest() {
        UUID taskId = UUID.randomUUID();

        Task task = Task.builder()
                .id(taskId)
                .content("task to delete")
                .user(User.builder()
                        .username("user1")
                        .password("pass1")
                        .id(UUID.randomUUID())
                        .build())
                .build();
        TaskDTO taskDto =  TaskDTO.builder()
                .content("test task")
                .id(task.getId())
                .build();

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        when(taskMapper.taskToTaskDto(task)).thenReturn(taskDto);

        Optional<TaskDTO> result = taskService.deleteTask(taskId);

        Assertions.assertThat(result.isPresent());
        Assertions.assertThat(taskId).isEqualTo(result.get().getId());
        Assertions.assertThat(result).isNotEmpty();
    }

















}