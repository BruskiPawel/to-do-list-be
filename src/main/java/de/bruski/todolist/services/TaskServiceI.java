package de.bruski.todolist.services;

import de.bruski.todolist.models.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskServiceI {
    List<Task> getAllTasks();

    Task createNewTask(Task task);

    void deleteTask(UUID id);

    void addSortedTaskList(Iterable<Task> tasks);

    Optional<Task> getTaskById(UUID id);

}
