package de.bruski.todolist.repositories;

import de.bruski.todolist.entities.Task;
import de.bruski.todolist.models.TaskDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findAll();

    Task findByContent(String content);

    List <Task> findTasksByDate(LocalDate localDate);

}
