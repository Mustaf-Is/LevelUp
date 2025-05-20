package com.example.levelup.DTOs;

import com.example.levelup.Enums.Frequency;

import java.time.LocalDateTime;

public record HabitDTO(
        int id,
        String title,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Frequency frequency,
        int times,
        int userId,
        int categoryId
) {
}
