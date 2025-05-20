package com.example.levelup.mappers;

import com.example.levelup.DTOs.CategoryDTO;
import com.example.levelup.DTOs.CreateCategoryDTO;
import com.example.levelup.DTOs.HabitDTO;
import com.example.levelup.models.Category;
import com.example.levelup.models.Habit;
import com.example.levelup.repositories.CategoryRepository;
import com.example.levelup.repositories.HabitRepository;
import com.example.levelup.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {


    private final HabitRepository habitRepository;

    public CategoryMapper(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();

        category.setId(categoryDTO.id());
        category.setName(categoryDTO.name());
        category.setDescription(categoryDTO.description());

        return category;
    }

    public Category toCreateEntity(CreateCategoryDTO createCategoryDTO) {
        Category category = new Category();

        category.setId(createCategoryDTO.id());
        category.setName(createCategoryDTO.name());
        category.setDescription(createCategoryDTO.description());

        return category;
    }

    public CategoryDTO toDTO(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                toHabitDTOs(category.getHabits())

        );
    }

    public CreateCategoryDTO toCreateDTO(Category category) {
        return new CreateCategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    public static List<HabitDTO> toHabitDTOs(List<Habit> habits) {
        return habits.stream()
                .map(habit -> new HabitDTO(
                        habit.getId(),
                        habit.getTitle(),
                        habit.getDescription(),
                        habit.getStartDate(),
                        habit.getEndDate(),
                        habit.getUser() != null ? habit.getUser().getId() : 0,
                        habit.getCategory() != null ? habit.getCategory().getId() : 0
                ))
                .collect(Collectors.toList());
    }


}
