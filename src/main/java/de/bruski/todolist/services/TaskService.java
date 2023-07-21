package de.bruski.todolist.services;

import de.bruski.todolist.entities.Task;
import de.bruski.todolist.mappers.TaskMapper;
import de.bruski.todolist.models.TaskDTO;
import de.bruski.todolist.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    public Iterable<Task> getAllTasks() {

        return taskRepository.findAll();
    }

    public  List<TaskDTO> getAllTasksByUser(UUID id) {
        return taskMapper.taskListToTaskDtoList(taskRepository.findAllByUserId(id));
    }

    public void createNewTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void addSortedTaskList(Iterable<Task> tasks) {
        taskRepository.deleteAll();
        taskRepository.saveAll(tasks);
    }
}
