package de.bruski.todolist.repositories;

import de.bruski.todolist.models.TaskDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskDTO, UUID> {
    List<TaskDTO> findAll();

    TaskDTO findByContent(String content);

    List <TaskDTO> findTasksByDate(LocalDate localDate);

}
