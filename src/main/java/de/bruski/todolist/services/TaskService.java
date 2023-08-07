package de.bruski.todolist.services;

import de.bruski.todolist.entities.Task;
import de.bruski.todolist.entities.User;
import de.bruski.todolist.mappers.TaskMapper;
import de.bruski.todolist.models.TaskDTO;
import de.bruski.todolist.models.UserDTO;
import de.bruski.todolist.repositories.TaskRepository;
import de.bruski.todolist.repositories.UserRepository;
import de.bruski.todolist.security.JWTTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final TaskMapper taskMapper;

    private final JWTTokenProvider jwtTokenProvider;

    public Iterable<Task> getAllTasks() {

        return taskRepository.findAll();
    }

    public  List<TaskDTO> getAllTasksByUser(UserDTO userDto) {
        User user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return taskMapper.taskListToTaskDtoList(taskRepository.getTasksByUser(user));
    }
//    public  List<Task> getAllTasksByUser(UUID id) {
//        return taskRepository.findAllByUserId(id);
//    }
    public TaskDTO createNewTask(TaskDTO taskDto) {
        User user = userRepository.findById(taskDto.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Task task = taskMapper.taskDtoToTask(taskDto);
        task.setUser(user);
        return taskMapper.taskToTaskDto(taskRepository.save(task));
    }

    public TaskDTO deleteTask(UserDTO userDto, UUID id) {
            User user = userRepository.findByUsername(userDto.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            Optional<Task> task = taskRepository.getTaskByUserAndId(user, id);
            task.ifPresent(taskRepository::delete);
            return taskMapper.taskToTaskDto(task.get());
        }
//    public void addSortedTaskList(Iterable<Task> tasks) {
//        taskRepository.deleteAll();
//        taskRepository.saveAll(tasks);
//    }
}
