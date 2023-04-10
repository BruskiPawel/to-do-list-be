package de.bruski.todolist.services;

import de.bruski.todolist.models.Task;
import de.bruski.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Iterable<Task> getAllTasks() {
        System.out.println("here is service logic done on the server side");
        return taskRepository.findAll();
    }

    public void createNewTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        System.out.println("befor deleting");
        taskRepository.deleteById(id);
        System.out.println("After deleting");
    }
}
