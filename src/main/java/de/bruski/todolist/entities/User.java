package de.bruski.todolist.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @JdbcTypeCode(SqlTypes.CHAR)
        @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
        private UUID id;
        @Column(name = "user_name")
        private String username;
        @Column(name = "email")
        private String email;
        @Column(name = "password")
        private String password;

        @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
        private Set<Task> taskList;
    }

