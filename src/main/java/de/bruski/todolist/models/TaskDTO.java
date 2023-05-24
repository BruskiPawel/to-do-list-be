package de.bruski.todolist.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "my_tasks")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "content")
    private String content;
}
