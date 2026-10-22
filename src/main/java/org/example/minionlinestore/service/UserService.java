package org.example.minionlinestore.service;

import org.example.minionlinestore.dto.ProductDTO;
import org.example.minionlinestore.dto.UserDTO;
import org.example.minionlinestore.mapper.UserMapper;
import org.example.minionlinestore.model.Product;
import org.example.minionlinestore.model.User;
import org.example.minionlinestore.repository.RoleRepository;
import org.example.minionlinestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public List<UserDTO>  getUsers()
    {return userMapper.toDTOs(userRepository.findAll());}

    public UserDTO getUser(Long id){
        return userMapper.toDTO(userRepository.findById(id).orElse(new User()));
    }

    public UserDTO updateUser(UserDTO userDTO){
        return userMapper.toDTO(userRepository.save(userMapper.toModel(userDTO)));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }


    public UserDTO updateCurrentUserProfile(UserDTO userDTO) {
        User currentUser = getCurrentSessionUser();

        if (currentUser == null) {
            throw new RuntimeException("User is not authenticated");
        }

        currentUser.setFullName(userDTO.getFullName());
        currentUser.setEmail(userDTO.getEmail());

        User savedUser = userRepository.save(currentUser);

        return userMapper.toDTO(savedUser);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user!=null){
            return user;
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }

    public UserDTO addUser(UserDTO user){
        User userModal = userMapper.toModel(user);
        User checkUser = userRepository.findByEmail(userModal.getEmail());
        if(checkUser==null){
            userModal.setPassword(passwordEncoder.encode(userModal.getPassword()));
            return userMapper.toDTO(userRepository.save(userModal));
        }
        return null;
    }

    public User updatePassword(String newPassword, String oldPassword) {
        User currentUser = getCurrentSessionUser();
        if (passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(currentUser);
            return currentUser;
        }
        return null;
    }

    public User getCurrentSessionUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User user = (User) authentication.getPrincipal();
            if(user!=null){
                return user;
            }
        }
        return  null;
    }

    public UserDTO getCurrentUserDTO() {
        User user = getCurrentSessionUser();

        if (user == null) {
            throw new RuntimeException("User is not authenticated");
        }

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());

        return dto;
    }
}
