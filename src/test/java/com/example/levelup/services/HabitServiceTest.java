package com.example.levelup.services;

import com.example.levelup.models.Category;
import com.example.levelup.models.Habit;
import com.example.levelup.models.User;
import com.example.levelup.repositories.HabitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HabitServiceTest {

    @Mock
    private HabitRepository habitRepository;

    @InjectMocks
    private HabitService habitService;


    @Test
    void testGetAllHabits() {
        Habit h1 = new Habit();
        h1.setId(1);
        h1.setTitle("Test Habit");
        h1.setDescription("Test Habit Description");
        h1.setStartDate(LocalDateTime.now());


        User user = new User();
        user.setId(1);
        user.setFirstName("Test");
        user.setLastName("User");
        user.setUsername("Testuser");
        user.setPassword("Testpassword");
        user.setEmail("Test@test.com");
        user.setPhone("123456789");

        h1.setUser(user);


        Category category = new Category();
        category.setId(1);
        category.setName("Health Category 2");
        category.setDescription("This is a Health Category Description");

        h1.setCategory(category);


        Habit h2 = new Habit();
        h2.setId(1);
        h2.setTitle("Test Habit 2");
        h2.setDescription("Test Habit Description 2");
        h2.setStartDate(LocalDateTime.now());
        h2.setUser(user);
        h2.setCategory(category);


        Mockito.when(habitRepository.findAll()).thenReturn(Arrays.asList(h1, h2));

        List<Habit> habits = habitService.getAllHabits();

        assertEquals(2, habits.size());


    }

    @Test
    void testCreateHabit() {
        Habit habit = new Habit();
        habit.setId(1);
        habit.setTitle("Test Habit");
        habit.setDescription("Test Habit Description");
        habit.setStartDate(LocalDateTime.now());

        User user = new User();
        user.setId(1);
        user.setFirstName("Test");
        user.setLastName("User");
        user.setUsername("Testuser");
        user.setPassword("Testpassword");
        user.setEmail("Test@test.com");
        user.setPhone("123456789");
        habit.setUser(user);

        Category category = new Category();
        category.setId(1);
        category.setName("Health Category 2");
        category.setDescription("This is a Health Category Description");

        habit.setCategory(category);
        Mockito.when(habitRepository.save(habit)).thenReturn(habit);
        Habit addedHabit = habitService.createHabit(habit);

        assertEquals(habit.getId(), addedHabit.getId());
        assertEquals(habit.getTitle(), addedHabit.getTitle());
        assertEquals(habit.getDescription(), addedHabit.getDescription());
        assertEquals(habit.getStartDate(), addedHabit.getStartDate());
        assertEquals(habit.getCategory(), addedHabit.getCategory());
        assertEquals(habit.getUser(), addedHabit.getUser());

        //assertThat(addedHabit).usingRecursiveComparison().isEqualTo(habit);

        verify(habitRepository, times(1)).save(habit);

    }

    @Test
    void testUpdateHabit() {
        Habit existingHabit = new Habit();
        existingHabit.setId(1);
        existingHabit.setTitle("Old Title");
        existingHabit.setDescription("Old Desc");
        existingHabit.setStartDate(LocalDateTime.of(2024, 1, 1, 10, 0));
        existingHabit.setEndDate(LocalDateTime.of(2024, 12, 31, 10, 0));
        existingHabit.setUser(new User());
        existingHabit.setCategory(new Category());

        Habit updatedHabit = new Habit();
        updatedHabit.setTitle("New Title");
        updatedHabit.setDescription("New Desc");
        updatedHabit.setStartDate(LocalDateTime.of(2025, 1, 1, 10, 0));
        updatedHabit.setEndDate(LocalDateTime.of(2025, 12, 31, 10, 0));
        updatedHabit.setUser(new User());
        updatedHabit.setCategory(new Category());


        Mockito.when(habitRepository.findById(existingHabit.getId())).thenReturn(Optional.of(existingHabit));
        Mockito.when(habitRepository.save(existingHabit)).thenReturn(existingHabit);

        Habit result = habitService.updateHabit(existingHabit.getId(), updatedHabit);

        assertEquals("New Title", result.getTitle());
        assertEquals("New Desc", result.getDescription());
        assertEquals(LocalDateTime.of(2025, 1, 1, 10, 0), result.getStartDate());
        assertNotNull(result.getUser());
        assertNotNull(result.getCategory());

        verify(habitRepository).save(existingHabit);
    }

    @Test
    void testGetHabitById() {
        Habit habit = new Habit();
        habit.setId(1);
        habit.setTitle("Workout");

        Mockito.when(habitRepository.findById(1)).thenReturn(Optional.of(habit));

        Habit fetchedHabit = habitService.getHabitById(1);

        assertNotNull(fetchedHabit);
        assertEquals("Workout", fetchedHabit.getTitle());
    }


    @Test
    void testDeleteHabit() {
        habitService.deleteHabit(1);
        verify(habitRepository, times(1)).deleteById(1);
    }


}
