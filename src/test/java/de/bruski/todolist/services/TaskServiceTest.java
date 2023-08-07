package de.bruski.todolist.services;

import de.bruski.todolist.entities.Task;
import de.bruski.todolist.entities.User;
import de.bruski.todolist.mappers.TaskMapper;
import de.bruski.todolist.models.TaskDTO;
import de.bruski.todolist.models.UserDTO;
import de.bruski.todolist.repositories.TaskRepository;
import de.bruski.todolist.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
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

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskMapper taskMapper;

    @Test
    public void createNewTaskTest() {
        User user = User.builder()
                .username("user1")
                .password("password1")
                .id(UUID.randomUUID())
                .build();

        Task task = Task.builder()
                .content("test task")
                .id(UUID.randomUUID())
                .user(user)
                .build();

        TaskDTO taskDto = TaskDTO.builder()
                .content("test task")
                .id(task.getId())
                .userId(user.getId())
                .build();

    when(userRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(user));
    when(taskRepository.save(any(Task.class))).thenReturn(task);

    when(taskMapper.taskDtoToTask(any(TaskDTO.class))).thenReturn(task);
    when(taskMapper.taskToTaskDto(any(Task.class))).thenReturn(taskDto);

    TaskDTO savedTask = taskService.createNewTask(taskDto);
        UUID id = task.getId();

        Assertions.assertThat(savedTask).isNotNull();
        Assertions.assertThat(savedTask.getId()).isEqualTo(id);
        Assertions.assertThat(taskRepository.getTasksByUser(task.getUser())).isNotNull();
    }


    @Test
    public void GetAllTaskByUserTest() {
        UUID userId = UUID.randomUUID();
        User user = User.builder()
                .username("user1")
                .password("password1")
                .id(userId)
                .build();

        List<Task> listOfTasks = List.of(
                Task.builder()
                        .content("test task")
                        .id(UUID.randomUUID())
                        .user(user)
                        .build(),
                Task.builder()
                        .content("test task2")
                        .id(UUID.randomUUID())
                        .user(user)
                        .build()
        );

        List<TaskDTO> listOfTaskDtos = List.of(
                TaskDTO.builder()
                        .content("test task")
                        .id(listOfTasks.get(0).getId())
                        .userId(userId)
                        .build(),
                TaskDTO.builder()
                        .content("test task2")
                        .id(listOfTasks.get(1).getId())
                        .userId(userId)
                        .build()
        );

        UserDTO userDto = UserDTO.builder()
                .username(user.getUsername())
                .build();

        when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.ofNullable(user));
        when(taskRepository.getTasksByUser(listOfTasks.get(0).getUser())).thenReturn(listOfTasks);
        when(taskMapper.taskListToTaskDtoList(listOfTasks)).thenReturn(listOfTaskDtos);

        List<TaskDTO> allTasks = taskService.getAllTasksByUser(userDto);

        //Assertions.assertThat(allTasks).isNotNull().hasSize(2);
    }



    @Test
    public void deleteTaskTest() {
        UUID taskId = UUID.randomUUID();
        User user = User.builder()
                .username("user1")
                .password("password1")
                .id(UUID.randomUUID())
                .build();

        Task task = Task.builder()
                .id(taskId)
                .content("task to delete")
                .user(user)
                .build();

        TaskDTO taskDto =  TaskDTO.builder()
                .content("test task")
                .id(task.getId())
                .build();

        UserDTO userDto = UserDTO.builder()
                .username(user.getUsername())
                .build();

        when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.of(user));
        when(taskRepository.getTaskByUserAndId(user, taskId)).thenReturn(Optional.of(task));
        when(taskMapper.taskToTaskDto(task)).thenReturn(taskDto);

        TaskDTO result = taskService.deleteTask(userDto, taskId);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(taskId).isEqualTo(result.getId());
    }

















}