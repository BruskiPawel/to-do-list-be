package de.bruski.todolist.controllers;

import de.bruski.todolist.models.Task;
import de.bruski.todolist.models.User;
import de.bruski.todolist.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;


@CrossOrigin
@RestController()
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/new_user")
    public ResponseEntity<?> addNewUser(@RequestBody User user){
        userService.addNewUser(user);
        return ResponseEntity.ok("Added");
    }

    @PostMapping("/login_user")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws NoSuchAlgorithmException {
        ResponseEntity<?> responseEntity = userService.loginUser(user);
        return ResponseEntity.ok(true);
    }



}
