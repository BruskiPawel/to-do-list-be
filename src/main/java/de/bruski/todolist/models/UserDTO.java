package de.bruski.todolist.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDTO {

    private UUID id;
    @NotNull
    @NotBlank
    private String username;

    private String email;

    private String password;
}