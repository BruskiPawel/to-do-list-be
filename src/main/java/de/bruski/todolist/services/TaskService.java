package de.bruski.todolist.services;

import de.bruski.todolist.models.Task;
import de.bruski.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Iterable<Task> getAllTasks() {

        return taskRepository.findAll();
    }

    public  List<Task> getAllTasksByUser(UUID id) {
        return taskRepository.findAllByUserId(id);
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
