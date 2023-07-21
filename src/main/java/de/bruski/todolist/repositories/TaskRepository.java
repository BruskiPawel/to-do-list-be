package de.bruski.todolist.repositories;

import de.bruski.todolist.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAll();

    List<Task> findAllByUserId(UUID userId);
}
