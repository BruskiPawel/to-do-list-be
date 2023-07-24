package de.bruski.todolist.controllers;


import de.bruski.todolist.entities.Task;
import de.bruski.todolist.models.TaskDTO;
import de.bruski.todolist.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@CrossOrigin
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/api/tasks")
    public String getAllTaskByUser(@PathVariable UUID id) throws Exception {
        List<TaskDTO> tasks = taskService.getAllTasksByUser(id);
        System.out.println(tasks);
        return "tasks";
    }

    @PostMapping("/add-new-task")
    public ResponseEntity<?> addNewTask(@RequestBody Task task) {
        taskService.createNewTask(task);
        return ResponseEntity.ok("Added");
    }

    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("deleted!");
    }
}
