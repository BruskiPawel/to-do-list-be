package de.bruski.todolist.controllers;


import de.bruski.todolist.models.Task;
import de.bruski.todolist.repositories.TaskRepository;
import de.bruski.todolist.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@CrossOrigin
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<?> getAllTask() throws Exception {
        Iterable<Task> tasks = taskService.getAllTasks();
        tasks.forEach(task -> System.out.println(task.getUser()));
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/add-new-task")
    public ResponseEntity<?> addNewTask(@RequestBody Task task) {
        taskService.createNewTask(task);
        return ResponseEntity.ok("Added");
    }

    @PostMapping("/post_sorted_tasks")
    public ResponseEntity<?> postSortedTasks(@RequestBody List<Task> tasks) {
        taskService.addSortedTaskList(tasks);
        return ResponseEntity.ok("Added");
    }

    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }
}
