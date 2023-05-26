package de.bruski.todolist.controllers;

import de.bruski.todolist.models.UserDTO;
import de.bruski.todolist.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;


@CrossOrigin
@RestController()
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/new_user")
    public ResponseEntity<?> addNewUser(@Validated @RequestBody UserDTO user){
        UserDTO savedUser = userService.addNewUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login_user")
    public ResponseEntity<?> loginUser(@Validated @RequestBody UserDTO user) throws NoSuchAlgorithmException {
        ResponseEntity<?> responseEntity = userService.loginUser(user);
        return ResponseEntity.ok("User logged in successfully.");
    }



}
