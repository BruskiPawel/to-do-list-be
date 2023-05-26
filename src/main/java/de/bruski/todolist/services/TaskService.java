package de.bruski.todolist.services;

import de.bruski.todolist.entities.Task;
import de.bruski.todolist.mapper.TaskMapper;
import de.bruski.todolist.models.TaskDTO;
import de.bruski.todolist.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService implements TaskServiceI {

    @Autowired
    private TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    public List<TaskDTO> getAllTasks() {
        return taskMapper.taskListToTaskDtoList(taskRepository.findAll());
    }

    public TaskDTO createNewTask(TaskDTO task) {
        return taskMapper.taskToTaskDto(taskRepository.save(taskMapper.taskDtoToTask(task)));
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

//    public void addSortedTaskList(List<TaskDTO> tasks) {
//        taskRepository.deleteAll();
//        taskRepository.saveAll(taskMapper.taskDtoToTask(tasks));
//    }

    public Optional<TaskDTO> getTaskById(UUID id) {
        return Optional.ofNullable(taskMapper.taskToTaskDto(taskRepository.findById(id).orElse(null)));
    }
}
