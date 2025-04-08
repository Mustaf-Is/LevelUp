package com.example.levelup.repositories;

import com.example.levelup.models.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {

}
