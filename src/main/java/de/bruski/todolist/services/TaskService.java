package de.bruski.todolist.services;

import de.bruski.todolist.models.TaskDTO;
import de.bruski.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService implements TaskServiceI{
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskDTO> getAllTasks() {

        return taskRepository.findAll();
    }

    public TaskDTO createNewTask(TaskDTO task) {
        return taskRepository.save(task);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

    public void addSortedTaskList(Iterable<TaskDTO> tasks) {
        taskRepository.deleteAll();
        taskRepository.saveAll(tasks);
    }

    public Optional<TaskDTO> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }
}
