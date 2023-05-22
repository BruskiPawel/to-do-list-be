package de.bruski.todolist.repositories;

import de.bruski.todolist.models.Task;
import de.bruski.todolist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAll();

    Task findByContent(String content);

    List <Task> findTasksByDate(LocalDate localDate);

}
