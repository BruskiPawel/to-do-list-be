package de.bruski.todolist.repositories;

import de.bruski.todolist.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findById(UUID id);

    Boolean existsByUsername(String username);
    Boolean existsByPassword(String password);

}
