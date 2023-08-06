package de.bruski.todolist.services;

import de.bruski.todolist.entities.Task;
import de.bruski.todolist.entities.User;
import de.bruski.todolist.mappers.TaskMapper;
import de.bruski.todolist.models.TaskDTO;
import de.bruski.todolist.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    public Iterable<Task> getAllTasks() {

        return taskRepository.findAll();
    }

    public  List<TaskDTO> getAllTasksByUser(User user) {
        List<Task> allByUserId = taskRepository.getTasksByUser(user);
        return taskMapper.taskListToTaskDtoList(allByUserId);
    }
//    public  List<Task> getAllTasksByUser(UUID id) {
//        return taskRepository.findAllByUserId(id);
//    }
    public TaskDTO createNewTask(TaskDTO task) {
        return taskMapper.taskToTaskDto(taskRepository.save(taskMapper.taskDtoToTask(task)));
    }

    public Optional<TaskDTO> deleteTask(UUID id) {
            Optional<Task> task = taskRepository.getTaskById(id);
            task.ifPresent(taskRepository::delete);
            return Optional.of(taskMapper.taskToTaskDto(task.get()));
        }
//    public void addSortedTaskList(Iterable<Task> tasks) {
//        taskRepository.deleteAll();
//        taskRepository.saveAll(tasks);
//    }
}
