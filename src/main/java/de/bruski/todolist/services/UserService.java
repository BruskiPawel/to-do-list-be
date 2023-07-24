package de.bruski.todolist.services;

import de.bruski.todolist.entities.User;
import de.bruski.todolist.repositories.UserRepository;
import de.bruski.todolist.webconfig.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Encryptor encryptor;

    public void addNewUser(User user) {
        String encryptedPassword;
        try {
            encryptedPassword = encryptor.encryptString(user.getPassword());
            user.setPassword(encryptedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        userRepository.save(user);
        System.out.println("saved");
    }

    public String loginUser(User user) throws NoSuchAlgorithmException {
//            User userDB = userRepository.findByUsername(user.getUsername());
//        if(userDB == null) {
//            System.out.println("Invalid username");
//            throw new IllegalArgumentException("Invalid username");
//        }
//        if(userDB.getPassword().equals(encryptor.encryptString(user.getPassword()))){
//            System.out.println("Logedin !");
//        } else {
//            throw new IllegalArgumentException("Invalid Password!");
//        }
//        return ResponseEntity.ok(true);
        if(isValidCredentials(user.getUsername(), user.getPassword())) {
            return "Logged in Success";
        }
        else {
            return "failed";
        }
    }
    private boolean isValidCredentials(String username, String password) throws NoSuchAlgorithmException {
        String encryptedPassword = encryptor.encryptString(password);
        return userRepository.existsUserByPassword(password) && userRepository.existsUserByUsername(username);
    }
}
