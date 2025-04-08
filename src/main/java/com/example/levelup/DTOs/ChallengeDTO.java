package com.example.levelup.DTOs;

import java.time.LocalDateTime;

public record ChallengeDTO(
        int id,
        String name,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate

) {
}
