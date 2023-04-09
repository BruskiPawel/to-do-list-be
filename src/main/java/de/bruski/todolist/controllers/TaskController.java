package de.bruski.todolist.controllers;


import de.bruski.todolist.models.Task;
import de.bruski.todolist.repositories.TaskRepository;
import de.bruski.todolist.services.TaskService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@CrossOrigin
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public Iterable<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
}
