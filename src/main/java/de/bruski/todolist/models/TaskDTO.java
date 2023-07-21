package de.bruski.todolist.models;


import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDTO {

    private UUID id;
    private LocalDate date;
    private LocalTime time;
    private String content;
}