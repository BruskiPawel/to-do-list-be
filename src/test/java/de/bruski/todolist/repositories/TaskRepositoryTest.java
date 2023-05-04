package de.bruski.todolist.repositories;

import de.bruski.todolist.models.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;
    @Test
    void testSaveTask() {

        Task jpaSaveTask = taskRepository.save(Task.builder().content("JPA save Test").build());

        Assertions.assertThat(jpaSaveTask).isNotNull();

    }
}