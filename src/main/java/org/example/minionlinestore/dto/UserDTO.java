package org.example.minionlinestore.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.minionlinestore.model.Role;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private Role role;
}
