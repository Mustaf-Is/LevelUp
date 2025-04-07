package com.example.levelup.services;

import com.example.levelup.DTOs.UserDTO;
import com.example.levelup.models.User;
import com.example.levelup.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(int id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setUsername(user.getUsername());
            existingUser.setPhone(user.getPhone());
        }
        //assert existingUser != null;
        return userRepository.save(existingUser);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}
