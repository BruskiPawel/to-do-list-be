package de.bruski.todolist.repositories;

import de.bruski.todolist.entities.User;
import de.bruski.todolist.models.UserDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void beforeEach() {
        List<User> expectedListOfUsers = getListOfUsersToTest();
        userRepository.saveAll(expectedListOfUsers);
    }

    @AfterEach
    void afterEach() {
        userRepository.deleteAll();
    }

    List<User> getListOfUsersToTest() {
        List<User> listOfTestUsers;
        File file = new File("src/test/resources/test_user_data");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
            listOfTestUsers = bufferedReader.lines().map(this::mapToUser).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listOfTestUsers;
    }

    User mapToUser(String line) {
        String[] splitedLine = line.split(";");
        return User.builder().username(splitedLine[0]).email(splitedLine[1]).password(splitedLine[2]).build();
    }

    @Test
    void shouldGetAllUsersFromDataBase() {
        // given
        List<User> expectedListOfUsers = getListOfUsersToTest();
        // when
        List<User> result = userRepository.findAll();
        // then
        Assertions.assertThat(expectedListOfUsers.size()).isEqualTo(result.size());
    }

    @Test
    void shouldFindUserByUsername() {
        //given
        String expectedUserName = "jessicamitchell";
        // when
        Optional<User> result = userRepository.findByUsername(expectedUserName);
        // then
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result.get().getUsername()).isEqualTo(expectedUserName);
    }

    @Test
    void shouldNotFindUserByUsername() {
        //given
        String expectedUserName = "paul";
        // when
        Optional<User> result = userRepository.findByUsername(expectedUserName);
        // then
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    void shouldFindUserById() {
        // given
        UUID expectedId = userRepository.findAll().get(6).getId();
        // when
        Optional<User> result = userRepository.findById(expectedId);
        // then
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result.get().getId()).isEqualTo(expectedId);
    }

    @Test
    void shouldFindUserByEmail() {
        //given
        String expectedUserEmail = "williamjackson@example.com";
        // when
        Optional<User> result = userRepository.findByEmail(expectedUserEmail);
        // then
        Assertions.assertThat(result.get().getEmail()).isEqualTo(expectedUserEmail);
    }

    @Test
    void shouldNotFindUserByEmail() {
        //given
        String expectedUserEmail = "some.test.Email@example.com";
        // when
        Optional<User> result = userRepository.findByEmail(expectedUserEmail);
        // then
        Assertions.assertThat(result).isEmpty();
    }


}