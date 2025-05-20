package com.example.levelup.services;
import java.util.List;
import com.example.levelup.models.Habit;
import com.example.levelup.repositories.HabitRepository;
import org.springframework.stereotype.Service;

@Service
public class HabitService {

    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    public Habit getHabitById(int id) {
        return habitRepository.findById(id).orElse(null);
    }

    public Habit createHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public Habit updateHabit(int id, Habit habit) {
        Habit existingHabit = habitRepository.findById(id).orElse(null);
        if (existingHabit != null) {
            existingHabit.setTitle(habit.getTitle());
            existingHabit.setDescription(habit.getDescription());
            existingHabit.setStartDate(habit.getStartDate());
            existingHabit.setEndDate(habit.getEndDate());
            existingHabit.setFrequency(habit.getFrequency());
            existingHabit.setTimes(habit.getTimes());
            existingHabit.setUser(habit.getUser());
            existingHabit.setCategory(habit.getCategory());
        }
        return existingHabit != null ? habitRepository.save(existingHabit) : null;
    }
    public void deleteHabit(int id) {
        habitRepository.deleteById(id);
    }
}