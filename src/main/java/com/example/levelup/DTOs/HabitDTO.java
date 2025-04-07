package com.example.levelup.DTOs;

import java.time.LocalDateTime;

public record HabitDTO(
        int id,
        String title,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        int userId,
        int categoryId
) {
}
