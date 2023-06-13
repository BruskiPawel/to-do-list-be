package de.bruski.todolist.controllers;

import de.bruski.todolist.entities.User;
import de.bruski.todolist.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserControllerTestIT {

    @Autowired
    UserController userController;

    @Autowired
    UserRepository userRepository;

    @Test
    void shouldReturnAllTaskFoundByUser()  {
        User user = userRepository.findAll().get(0);
        System.out.println(user.getTaskList());
    }
}
