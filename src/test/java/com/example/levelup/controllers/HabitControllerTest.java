package com.example.levelup.controllers;

import com.example.levelup.DTOs.HabitDTO;
import com.example.levelup.mappers.HabitMapper;
import com.example.levelup.models.Habit;
import com.example.levelup.security.JwtAuthenticationFilter;
import com.example.levelup.services.HabitService;
import com.example.levelup.services.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(HabitController.class)
@AutoConfigureMockMvc(addFilters = false)
public class HabitControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockitoBean
    private HabitService habitService;

    @MockitoBean
    private HabitMapper habitMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;



    @Test
    void testCreateHabit() throws Exception {
        HabitDTO requestDto = new HabitDTO(0, "Drink Water", "Stay hydrated", LocalDateTime.now(), null, 1, 2);
        Habit habitEntity = new Habit();
        habitEntity.setTitle("Drink Water");
        habitEntity.setDescription("Stay hydrated");
        habitEntity.setStartDate(LocalDateTime.now());

        Habit savedHabit = new Habit();
        savedHabit.setId(1);
        savedHabit.setTitle("Drink Water");
        savedHabit.setDescription("Stay hydrated");
        savedHabit.setStartDate(LocalDateTime.now());

        HabitDTO responseDto = new HabitDTO(1, "Drink Water", "Stay hydrated", LocalDateTime.now(), null, 1, 2);

        Mockito.when(habitMapper.toEntity(requestDto)).thenReturn(habitEntity);
        Mockito.when(habitService.createHabit(habitEntity)).thenReturn(savedHabit);
        Mockito.when(habitMapper.toDTO(savedHabit)).thenReturn(responseDto);


        mockMvc.perform(post("/api/habits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Drink Water"))
                .andExpect(jsonPath("$.id").value(1));


    }


    @Test
    void testUpdateHabitSuccess() throws Exception {
        HabitDTO requestDto = new HabitDTO(1, "Updated Habit", "Updated description", LocalDateTime.now(), null, 1, 2);
        Habit habitEntity = new Habit();
        habitEntity.setTitle("Updated Habit");
        habitEntity.setDescription("Updated description");

        Habit updatedHabit = new Habit();
        updatedHabit.setId(1);
        updatedHabit.setTitle("Updated Habit");
        updatedHabit.setDescription("Updated description");

        HabitDTO responseDto = new HabitDTO(1, "Updated Habit", "Updated description", LocalDateTime.now(), null, 1, 2);

        Mockito.when(habitMapper.toEntity(requestDto)).thenReturn(habitEntity);
        Mockito.when(habitService.updateHabit(1, habitEntity)).thenReturn(updatedHabit);
        Mockito.when(habitMapper.toDTO(updatedHabit)).thenReturn(responseDto);

        mockMvc.perform(put("/api/habits/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Updated Habit"));
    }

    @Test
    void testUpdateHabitNotFound() throws Exception {
        HabitDTO requestDto = new HabitDTO(1, "Updated Habit", "Updated description", LocalDateTime.now(), null, 1, 2);
        Habit habitEntity = new Habit();
        habitEntity.setTitle("Updated Habit");
        habitEntity.setDescription("Updated description");

        Mockito.when(habitMapper.toEntity(requestDto)).thenReturn(habitEntity);
        Mockito.when(habitService.updateHabit(1, habitEntity)).thenReturn(null);

        mockMvc.perform(put("/api/habits/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }



    @Test
    void testGetAllHabits() throws Exception {
        Habit habit1 = new Habit();
        habit1.setId(1);
        habit1.setTitle("Habit 1");

        Habit habit2 = new Habit();
        habit2.setId(2);
        habit2.setTitle("Habit 2");

        List<Habit> habits = List.of(habit1, habit2);

        HabitDTO dto1 = new HabitDTO(1, "Habit 1", "", LocalDateTime.now(), null, 1, 2);
        HabitDTO dto2 = new HabitDTO(2, "Habit 2", "", LocalDateTime.now(), null, 1, 2);

        Mockito.when(habitService.getAllHabits()).thenReturn(habits);
        Mockito.when(habitMapper.toDTO(habit1)).thenReturn(dto1);
        Mockito.when(habitMapper.toDTO(habit2)).thenReturn(dto2);

        mockMvc.perform(get("/api/habits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }


    @Test
    void testGetHabitByIdSuccess() throws Exception {
        Habit habit = new Habit();
        habit.setId(1);
        habit.setTitle("Drink Water");
        habit.setDescription("Stay hydrated");

        HabitDTO responseDto = new HabitDTO(1, "Drink Water", "Stay hydrated", LocalDateTime.now(), null, 1, 2);

        Mockito.when(habitService.getHabitById(1)).thenReturn(habit);
        Mockito.when(habitMapper.toDTO(habit)).thenReturn(responseDto);

        mockMvc.perform(get("/api/habits/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Drink Water"));
    }


    @Test
    void testGetHabitByIdNotFound() throws Exception {
        Habit habit = new Habit();
        habit.setId(1);
        habit.setTitle("Drink Water");
        habit.setDescription("Stay hydrated");

        HabitDTO responseDto = new HabitDTO(1, "Drink Water", "Stay hydrated", LocalDateTime.now(), null, 1, 2);

        Mockito.when(habitService.getHabitById(1)).thenReturn(null);
        Mockito.when(habitMapper.toDTO(habit)).thenReturn(responseDto);

        mockMvc.perform(get("/api/habits/1"))
                .andExpect(status().isNotFound());

    }



    @Test
    void testDeleteHabitSuccess() throws Exception {
        int habitId = 1;
        Habit habit = new Habit();
        habit.setId(habitId);

        Mockito.when(habitService.getHabitById(habitId)).thenReturn(habit);
        Mockito.doNothing().when(habitService).deleteHabit(habitId);

        mockMvc.perform(delete("/api/habits/{id}", habitId))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteHabitNotFound() throws Exception {
        int habitId = 999;

        Mockito.when(habitService.getHabitById(habitId)).thenReturn(null);

        mockMvc.perform(delete("/api/habits/{id}", habitId))
                .andExpect(status().isNotFound());
    }





}
