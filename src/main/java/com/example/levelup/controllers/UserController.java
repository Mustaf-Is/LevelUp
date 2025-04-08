package com.example.levelup.controllers;

import com.example.levelup.DTOs.CreateUserDTO;
import com.example.levelup.DTOs.UserDTO;
import com.example.levelup.mappers.UserMapper;
import com.example.levelup.models.User;
import com.example.levelup.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping
    public ResponseEntity<CreateUserDTO> createUser(@RequestBody CreateUserDTO userDTO) {
        User user = UserMapper.toCreateEntity(userDTO);
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(UserMapper.toCreateDTO(savedUser), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOs = users.stream()
                .map(UserMapper::toDTOWithHabits).collect(Collectors.toList());
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable int id) {
        User user = userService.getUserById(id);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDTO userDTO = UserMapper.toDTOWithHabits(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateUserDTO> updateUser(@PathVariable int id, @RequestBody CreateUserDTO userDTO) {
        User updatedUser = userService.updateUser(id, UserMapper.toCreateEntity(userDTO));
        return new ResponseEntity<>(UserMapper.toCreateDTO(updatedUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        User user = userService.getUserById(id);
        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
