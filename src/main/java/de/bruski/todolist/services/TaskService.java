package de.bruski.todolist.services;

import de.bruski.todolist.models.Task;
import de.bruski.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService implements TaskServiceI{
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {

        return taskRepository.findAll();
    }

    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

    public void addSortedTaskList(Iterable<Task> tasks) {
        taskRepository.deleteAll();
        taskRepository.saveAll(tasks);
    }

    public Optional<Task> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }
}
