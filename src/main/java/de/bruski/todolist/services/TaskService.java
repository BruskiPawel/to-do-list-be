package de.bruski.todolist.services;

import de.bruski.todolist.models.Task;
import de.bruski.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Iterable<Task> getAllTasks() {

        return taskRepository.findAll();
    }

    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

//    public void addSortedTaskList(Iterable<Task> tasks) {
//        taskRepository.deleteAll();
//        taskRepository.saveAll(tasks);
//    }

    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }

    public Optional<Task> getTaskById(long id) {
        if(!taskRepository.findById(id).isEmpty()) {
            return taskRepository.findById(id);
        }
        return null;
    }
}
