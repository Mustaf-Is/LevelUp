package com.example.levelup.repositories;
import com.example.levelup.models.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface HabitRepository extends JpaRepository<Habit, Integer> {

}
