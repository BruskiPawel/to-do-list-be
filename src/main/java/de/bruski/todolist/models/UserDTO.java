package de.bruski.todolist.models;

import lombok.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDTO {

    private UUID id;
    private String username;
    private String email;
    private String password;
}
