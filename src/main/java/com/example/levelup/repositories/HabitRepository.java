package com.example.levelup.repositories;
import com.example.levelup.models.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Integer> {

    List<Habit> findByUserId(int userId);
}
