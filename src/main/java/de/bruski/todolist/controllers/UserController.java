package de.bruski.todolist.controllers;

import de.bruski.todolist.entities.User;
import de.bruski.todolist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;


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

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) throws NoSuchAlgorithmException {
        System.out.println(userService.loginUser(user));
        return "loggedom";

    }

}
