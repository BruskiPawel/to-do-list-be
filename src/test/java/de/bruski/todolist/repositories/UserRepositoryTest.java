package de.bruski.todolist.repositories;

import de.bruski.todolist.entities.Role;
import de.bruski.todolist.entities.User;
import de.bruski.todolist.webconfig.Encryptor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Test
    public void findUserByNameTest() {
        Role role1 = Role.builder().name("ROLE_USER").build();
        Role role2 = Role.builder().name("ROLE_ADMIN").build();
        List<Role> roles = roleRepository.saveAll(List.of(role1, role2));

        User user = User.builder()
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .roles(roles)
                .build();

        User savedUser = userRepository.save(user);
        Optional<User> result = userRepository.findByUsername(savedUser.getUsername());

        Assertions.assertThat(result.isPresent());
        Assertions.assertThat(result.get().getUsername()).isEqualTo("testuser");
        Assertions.assertThat(result.get().getRoles()).hasSize(2);
    }

    @Test
    public void existsByUsernameTest() {
        Role role1 = Role.builder().name("ROLE_USER").build();
        Role role2 = Role.builder().name("ROLE_ADMIN").build();
        List<Role> roles = roleRepository.saveAll(List.of(role1, role2));

        User user = User.builder()
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .roles(roles)
                .build();

        User savedUser = userRepository.save(user);
        Boolean result = userRepository.existsByUsername(savedUser.getUsername());

        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void existsByPasswordTest() throws NoSuchAlgorithmException {
        Role role1 = Role.builder().name("ROLE_USER").build();
        Role role2 = Role.builder().name("ROLE_ADMIN").build();
        List<Role> roles = roleRepository.saveAll(List.of(role1, role2));

        User user = User.builder()
                .username("testuser")
                .email("test@example.com")
                .password("pass123")
                .roles(roles)
                .build();

        User savedUser = userRepository.save(user);
        Boolean result = userRepository.existsByPassword(savedUser.getPassword());

        Assertions.assertThat(result).isTrue();
    }

}