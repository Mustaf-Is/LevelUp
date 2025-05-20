package com.example.levelup.mappers;


import com.example.levelup.DTOs.HabitDTO;
import com.example.levelup.models.Habit;
import com.example.levelup.repositories.CategoryRepository;
import com.example.levelup.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HabitMapper {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public HabitMapper(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public HabitDTO toDTO(Habit habit) {
        return new HabitDTO(
                habit.getId(),
                habit.getTitle(),
                habit.getDescription(),
                habit.getStartDate(),
                habit.getEndDate(),
                habit.getFrequency(),
                habit.getTimes(),
                habit.getUser() != null ? habit.getUser().getId() : 0,
                habit.getCategory() != null ? habit.getCategory().getId() : 0
        );
    }

    public Habit toEntity(HabitDTO habitDTO) {
        Habit habit = new Habit();
        habit.setId(habitDTO.id());
        habit.setTitle(habitDTO.title());
        habit.setDescription(habitDTO.description());
        habit.setStartDate(habitDTO.startDate());
        habit.setEndDate(habitDTO.endDate());
        habit.setFrequency(habitDTO.frequency());
        habit.setTimes(habitDTO.times());
        if(habitDTO.userId() > 0) {
            habit.setUser(userRepository.findById(habitDTO.userId()).orElse(null));
        }
        if(habitDTO.categoryId() > 0) {
            habit.setCategory(categoryRepository.findById(habitDTO.categoryId()).orElse(null));
        }
        return habit;
    }
}
