package com.example.levelup.repositories;

import com.example.levelup.DTOs.UserDTO;
import com.example.levelup.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
