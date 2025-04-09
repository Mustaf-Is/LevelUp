package com.example.levelup.repositories;

import com.example.levelup.DTOs.UserDTO;
import com.example.levelup.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
