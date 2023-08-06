package de.bruski.todolist.repositories;

import de.bruski.todolist.entities.Role;
import de.bruski.todolist.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByNameTest() {
        //Arrange
        User user = User.builder()
                .username("lolek")
                .password("pass123")
                .build();
        User savedUser = userRepository.save(user);

            Role role = Role.builder().name("USER").build();
        //Act
            Optional<Role> savedRole = roleRepository.findByName("USER");
        //Assert
        System.out.println(savedRole);
    }
}