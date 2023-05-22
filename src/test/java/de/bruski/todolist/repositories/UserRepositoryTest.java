package de.bruski.todolist.repositories;
import de.bruski.todolist.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void beforeEach() {
        List<User> expectedListOfTask = getListOfUsersToTest();
        userRepository.saveAll(expectedListOfTask);
    }

    @AfterEach
    void afterEach() {
        userRepository.deleteAll();
    }

    List<User> getListOfUsersToTest() {
        List<User> listOfTestUsers = List.of();
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
        return User.builder().username(splitedLine[0]).eMail(splitedLine[1]).password(splitedLine[2]).build();
    }

    @Test
    void shouldGetAllUsersFromDataBase() {
        // given

        // when

        // then

    }


}