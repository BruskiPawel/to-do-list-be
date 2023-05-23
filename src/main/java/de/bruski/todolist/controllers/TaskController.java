package de.bruski.todolist.controllers;


import de.bruski.todolist.models.Task;
import de.bruski.todolist.repositories.TaskRepository;
import de.bruski.todolist.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController()
@CrossOrigin
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<?> getAllTask() throws Exception {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    @GetMapping("/task-by-id/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable UUID id) throws Exception {
       Optional<Task> tasks = taskService.getTaskById(id);
        return new ResponseEntity<>(tasks.get(), HttpStatus.OK);
    }

    @PostMapping("/add-new-task")
    public ResponseEntity<?> addNewTask(@RequestBody Task task) {
        taskService.createNewTask(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

//    @PostMapping("/post_sorted_tasks")
//    public ResponseEntity<?> postSortedTasks(@RequestBody List<Task> tasks) {
//        taskService.addSortedTaskList(tasks);
//        return ResponseEntity.ok("Added");
//    }

    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }
}
