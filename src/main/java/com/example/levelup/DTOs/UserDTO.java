package com.example.levelup.DTOs;


import com.example.levelup.models.Habit;

import java.util.List;


public record UserDTO(
        int id,
        String firstName,
        String lastName,
        String username,
        String password,
        String phone,
        List<HabitDTO> habits
) {
}
