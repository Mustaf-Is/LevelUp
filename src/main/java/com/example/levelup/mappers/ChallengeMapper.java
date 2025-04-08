package com.example.levelup.mappers;

import com.example.levelup.DTOs.ChallengeDTO;

public class ChallengeMapper {

    public ChallengeDTO toDTO(Challenge challenge) {
        return new ChallengeDTO(
                challenge.getId(),
                challenge.getName(),
                challenge.getDescription(),
                challenge.getStartDate(),
                challenge.getEndDate()
        );
    }

    public Challenge toEntity(ChallengeDTO challengeDTO) {
            Challenge challenge = new Challenge();
            challenge.setId(challengeDTO.id());
            challenge.setTitle(challengeDTO.name());
            challenge.setDescription(challengeDTO.description());
            challenge.setStartDate(challengeDTO.startDate());
            challenge.setEndDate(challengeDTO.endDate());
            return challenge;
    }
}
