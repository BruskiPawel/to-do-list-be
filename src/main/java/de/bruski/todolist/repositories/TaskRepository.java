package de.bruski.todolist.repositories;

import de.bruski.todolist.entities.Task;
import de.bruski.todolist.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAll();

    List<Task> getTasksByUser(User user);

    Optional<Task> getTaskById(UUID taskId);

    Optional<Task> getTaskByUserAndId(User user, UUID taskId);

    Optional<Task> deleteTaskByUserAndId(User user, UUID taskId);

}


