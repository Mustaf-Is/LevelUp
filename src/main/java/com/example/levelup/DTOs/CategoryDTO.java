package com.example.levelup.DTOs;

import java.util.List;

public record CategoryDTO(
        int id,
        String name,
        String description,
        List<HabitDTO> habits
) {

}
