package de.bruski.todolist.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "my_tasks")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "content")
    private String content;

}
