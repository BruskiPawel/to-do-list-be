package de.bruski.todolist.repositories;

import de.bruski.todolist.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void testSaveUser() {

        User savedUser = userRepository.save(User.builder().eMail("fdsfs@ffsd.com)").build());

        Assertions.assertThat(savedUser).isNotNull();

    }
}