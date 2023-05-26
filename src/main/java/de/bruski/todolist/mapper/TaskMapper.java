package de.bruski.todolist.mapper;

import de.bruski.todolist.entities.Task;
import de.bruski.todolist.models.TaskDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {

    TaskDTO taskToTaskDto(Task task);
    Task taskDtoToTask(TaskDTO dto);

    List<TaskDTO> taskListToTaskDtoList(List<Task> tasks);

    List<Task> taskDtoListToTaskList(List<TaskDTO> dtos);
}
