package de.bruski.todolist.repositories;

import de.bruski.todolist.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByPassword(String password);
}
