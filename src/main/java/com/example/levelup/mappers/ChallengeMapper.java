package com.example.levelup.mappers;

import com.example.levelup.DTOs.ChallengeDTO;
import com.example.levelup.models.Challenge;
import org.springframework.stereotype.Component;

@Component
public class ChallengeMapper {

    private final UserMapper userMapper;


    public ChallengeMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public ChallengeDTO toDTO(Challenge challenge) {
        return new ChallengeDTO(
                challenge.getId(),
                challenge.getName(),
                challenge.getDescription(),
                challenge.getStartDate(),
                challenge.getEndDate(),
                UserMapper.toDTO(challenge.getCreator())


        );
    }

    public Challenge toEntity(ChallengeDTO challengeDTO) {
            Challenge challenge = new Challenge();
            challenge.setId(challengeDTO.id());
            challenge.setName(challengeDTO.name());
            challenge.setDescription(challengeDTO.description());
            challenge.setStartDate(challengeDTO.startDate());
            challenge.setEndDate(challengeDTO.endDate());
            challenge.setCreator(UserMapper.toEntity(challengeDTO.creator()));
            return challenge;
    }
}
