package de.bruski.todolist.services;

import de.bruski.todolist.entities.User;
import de.bruski.todolist.mapper.UserMapper;
import de.bruski.todolist.models.UserDTO;
import de.bruski.todolist.repositories.UserRepository;
import de.bruski.todolist.webconfig.Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Encryptor encryptor;

    private final UserMapper userMapper;

    public UserDTO addNewUser(UserDTO user) {
        String encryptedPassword;
        try {
            encryptedPassword = encryptor.encryptString(user.getPassword());
            user.setPassword(encryptedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        System.out.println("saved");
        return userMapper.userToUserDto(userRepository.save(userMapper.userDtoToUser(user)));
    }

    public ResponseEntity<?> loginUser(UserDTO user) throws NoSuchAlgorithmException {
            Optional<User> userDB = userRepository.findByUsername(user.getUsername());
        if(userDB == null) {
            System.out.println("Invalid username");
            throw new IllegalArgumentException("Invalid username");
        }
        if(userDB.get().getPassword().equals(encryptor.encryptString(user.getPassword()))){
            System.out.println("Logedin !");
        } else {
            throw new IllegalArgumentException("Invalid Password!");
        }
        return ResponseEntity.ok(true);
    }
}
