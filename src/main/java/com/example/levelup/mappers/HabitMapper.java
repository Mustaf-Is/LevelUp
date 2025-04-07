package com.example.levelup.mappers;


import com.example.levelup.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class HabitMapper {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public HabitMapper(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }
}
