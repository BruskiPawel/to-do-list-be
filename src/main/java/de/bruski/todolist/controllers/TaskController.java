package de.bruski.todolist.controllers;


import de.bruski.todolist.entities.Task;
import de.bruski.todolist.entities.User;
import de.bruski.todolist.models.TaskDTO;
import de.bruski.todolist.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@CrossOrigin
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/api/tasks/{id}")
    public ResponseEntity<List<TaskDTO>> getAllTaskByUser(@PathVariable User user) throws Exception {
        List<TaskDTO> tasks = taskService.getAllTasksByUser(user);
        System.out.println(tasks);
        return ResponseEntity.ok(tasks);
    }
//@GetMapping("/api/tasks")
//public String getAllTaskByUser(@PathVariable UUID id) throws Exception {
//    List<Task> tasks = taskService.getAllTasksByUser(id);
//    System.out.println(tasks);
//    return "tasks";
//}

    @PostMapping("/add-new-task")
    public ResponseEntity<TaskDTO> addNewTask(@RequestBody TaskDTO task) {
        TaskDTO savedTaskDto = taskService.createNewTask(task);
        return ResponseEntity.ok(savedTaskDto);
    }

    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable UUID id) {
        Optional<TaskDTO> deletedTask = taskService.deleteTask(id);
        return ResponseEntity.ok(deletedTask.get());
    }
}
