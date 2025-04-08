package com.example.levelup.controllers;
import com.example.levelup.DTOs.ChallengeDTO;
import com.example.levelup.mappers.ChallengeMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    private final ChallengeService challengeService;
    private final ChallengeMapper challengeMapper;

    @Autowired
    public ChallengeController(ChallengeService challengeService, ChallengeMapper challengeMapper) {
        this.challengeService = challengeService;
        this.challengeMapper = challengeMapper;
    }

    @GetMapping
    public ResponseEntity<List<ChallengeDTO>> getAllChallenges() {
        List<Challenge> challenges = challengeService.getAllChallenges();
        List<ChallengeDTO> challengeDTOs = challenges.stream()
                .map(challengeMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(challengeDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChallengeDTO> getChallengeById(@PathVariable int id) {
        Challenge challenge = challengeService.getChallengeById(id);
        if (challenge == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(challengeMapper.toDTO(challenge), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ChallengeDTO> createChallenge(@RequestBody ChallengeDTO challengeDTO) {
        Challenge challenge = challengeMapper.toEntity(challengeDTO);
        Challenge createdChallenge = challengeService.createChallenge(challenge);
        return new ResponseEntity<>(challengeMapper.toDTO(createdChallenge), HttpStatus.CREATEDChallenge)
    }


    @PutMapping("/{id}")
    public ResponseEntity<ChallengeDTO> updateHabit(@PathVariable int id, @RequestBody ChallengeDTO challengeDTO) {
        Challenge updatedChallenge = challengeService.updateChallenge(id, challengeMapper.toEntity(challengeDTO));
        if (updatedChallenge == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(challengeMapper.toDTO(updatedChallenge), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable int id) {
        Challenge challenge = challengeService.getChallengeById(id);
        if (challenge == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        challengeService.deleteChallenge(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}