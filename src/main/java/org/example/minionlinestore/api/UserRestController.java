package org.example.minionlinestore.api;

import lombok.RequiredArgsConstructor;
import org.example.minionlinestore.dto.UserDTO;
import org.example.minionlinestore.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> userList() {return userService.getUsers();}

    @GetMapping(value="{id}")
    public UserDTO getUser(@PathVariable(name="id") Long id){
        return userService.getUser(id);
    }

    @PostMapping
    public UserDTO addUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @PutMapping
    public UserDTO updateUser(@RequestBody UserDTO user){
        return userService.updateUser(user);
    }

    @DeleteMapping(value = {"{id}"})
    @PreAuthorize("hasAnyRole('ROLE_SELLER', 'ROLE_ADMIN')")
    public void deleteUser(@PathVariable(name="id") Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/me")
    public UserDTO getCurrentUser() {
        return userService.getCurrentUserDTO();
    }

    @PutMapping("/profile")
    public UserDTO updateCurrentUserProfile(@RequestBody UserDTO userDTO) {
        return userService.updateCurrentUserProfile(userDTO);
    }

}
