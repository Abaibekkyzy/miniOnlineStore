package org.example.minionlinestore.mapper;

import org.example.minionlinestore.dto.UserDTO;
import org.example.minionlinestore.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface UserMapper {

    @Mapping(source = "permissions", target = "roles")
    UserDTO toDTO(User user);

    @Mapping(source = "roles", target = "permissions")
    User toModel(UserDTO user);

    List<UserDTO> toDTOs(List<User> users);
    List<User> toModels(List<UserDTO> users);
}
