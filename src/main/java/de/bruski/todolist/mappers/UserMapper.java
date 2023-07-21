package de.bruski.todolist.mappers;

import de.bruski.todolist.entities.User;
import de.bruski.todolist.models.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDTO userToUserDto(User user);
    User userDtoToUser(UserDTO dto);
}