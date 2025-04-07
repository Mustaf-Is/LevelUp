package com.example.levelup.DTOs;

import com.example.levelup.models.Habit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
//import lombok.data;


public record UserDTO(
        int id,
        String firstName,
        String lastName,
        String username,
        String password,
        String phone,
        List<Habit> habits
) {
}
