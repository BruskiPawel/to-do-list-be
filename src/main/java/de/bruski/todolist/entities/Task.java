package de.bruski.todolist.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
public class Task {

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @JdbcTypeCode(SqlTypes.CHAR)
        @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
        private UUID id;
        @Column(name = "date")
        private LocalDate date;

        @Column(name = "time")
        private LocalTime time;

        @Column(name = "content")
        private String content;

        @ManyToOne
        private User user;
    }

