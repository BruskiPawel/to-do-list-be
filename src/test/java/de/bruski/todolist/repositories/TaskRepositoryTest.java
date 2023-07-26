package de.bruski.todolist.repositories;

import de.bruski.todolist.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TaskRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void saveUserTest() {
        //Arrange
        User user = User.builder()
                .username("lolek")
                .password("pass123")
                .build();
        //Act
        User saveedUser = userRepository.save(user);
        //Assert
        Assertions.assertThat(saveedUser).isNotNull();
        Assertions.assertThat(saveedUser.getId()).isNotNull();
    }
}