package de.bruski.todolist.services;

import de.bruski.todolist.models.User;
import de.bruski.todolist.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void addNewUser(User user) {
        System.out.println("User service");
        throw new EntityExistsException("This username exists: " + user.getUserName());
    }
}
