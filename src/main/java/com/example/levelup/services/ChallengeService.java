package com.example.levelup.services;

import java.util.List;
import com.example.levelup.models.Challenge;
import com.example.levelup.repositories.ChallengeRepository;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }
    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }
    public Challenge getChallengeById(int id) {
        return challengeRepository.findById(id).orElse(null);
    }
    public Challenge createChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }
    public Challenge updateChallenge(int id, Challenge challenge) {
        Challenge existingChallenge = challengeRepository.findById(id).orElse(null);
        if (existingChallenge != null) {
            existingChallenge.setName(challenge.getName());
            existingChallenge.setDescription(challenge.getDescription());
            existingChallenge.setStartDate(challenge.getStartDate());
            existingChallenge.setEndDate(challenge.getEndDate());
        }
        return existingChallenge != null ? challengeRepository.save(existingChallenge) : null;
    }
    public void deleteChallenge(int id) {
        challengeRepository.deleteById(id);
    }
}
