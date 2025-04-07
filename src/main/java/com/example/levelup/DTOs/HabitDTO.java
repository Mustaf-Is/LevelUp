package com.example.levelup.DTOs;

public record HabitDTO(
        int id,
        String title,
        String description,
        String startDate,
        String endDate,
        String category,
        int userId,
        int categoryId,
        String categoryName
) {
}
