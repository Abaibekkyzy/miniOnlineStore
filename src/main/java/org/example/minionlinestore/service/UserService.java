package org.example.minionlinestore.service;

import org.example.minionlinestore.model.User;
import org.example.minionlinestore.repository.RoleRepository;
import org.example.minionlinestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user!=null){
            return user;
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }

    public User addUser(User user){
        User checkUser = userRepository.findByEmail(user.getEmail());
        if(checkUser==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        return null;
    }

}
