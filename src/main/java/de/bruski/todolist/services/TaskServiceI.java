package de.bruski.todolist.services;

import de.bruski.todolist.models.TaskDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskServiceI {
    List<TaskDTO> getAllTasks();

    TaskDTO createNewTask(TaskDTO task);

    void deleteTask(UUID id);

    //void addSortedTaskList(Iterable<TaskDTO> tasks);

    Optional<TaskDTO> getTaskById(UUID id);

}