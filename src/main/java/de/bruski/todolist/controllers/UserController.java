package de.bruski.todolist.controllers;

import de.bruski.todolist.models.Task;
import de.bruski.todolist.models.User;
import de.bruski.todolist.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController()
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new_user")
    public ResponseEntity<?> addNewUser(@RequestBody User user){
        userService.addNewUser(user);
        return ResponseEntity.ok("Added");
    }

}
