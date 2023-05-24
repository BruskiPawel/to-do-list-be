package de.bruski.todolist.repositories;

import de.bruski.todolist.models.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, UUID> {
    List<UserDTO> findAll();

    Optional<UserDTO> findByUsername(String username);

    Optional<UserDTO> findByEmail(String email);

    Optional<UserDTO> findById(UUID id);
}
