package de.bruski.todolist.entities;

import jakarta.persistence.*;
import lombok.*;
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
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;
        @Column(name = "user_name")
        private String username;
        @Column(name = "email")
        private String email;
        @Column(name = "password")
        private String password;
    }

