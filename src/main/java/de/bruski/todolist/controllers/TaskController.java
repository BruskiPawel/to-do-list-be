package de.bruski.todolist.controllers;

import de.bruski.todolist.entities.User;
import de.bruski.todolist.models.TaskDTO;
import de.bruski.todolist.models.UserDTO;
import de.bruski.todolist.security.JWTAuthenticationFilter;
import de.bruski.todolist.security.JWTTokenProvider;
import de.bruski.todolist.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/api/task")
@CrossOrigin
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> getAllTaskByUser(@RequestBody UserDTO user) throws Exception {
        List<TaskDTO> tasks = taskService.getAllTasksByUser(user);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDTO> addNewTask(@RequestBody TaskDTO task) {
        TaskDTO savedTaskDto = taskService.createNewTask(task);
        return new ResponseEntity<>(savedTaskDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable UUID id, @RequestBody UserDTO userDto) {
        taskService.deleteTask(userDto, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
