package com.example.levelup.controllers;

import com.example.levelup.DTOs.HabitDTO;
import com.example.levelup.mappers.HabitMapper;
import com.example.levelup.models.Habit;
import com.example.levelup.services.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    private final HabitService habitService;
    private final HabitMapper habitMapper;

    @Autowired
    public HabitController(HabitService habitService, HabitMapper habitMapper) {
        this.habitService = habitService;
        this.habitMapper = habitMapper;
    }

    @GetMapping
    public ResponseEntity<List<HabitDTO>> getAllHabits() {
        List<Habit> habits = habitService.getAllHabits();
        List<HabitDTO> habitDTOs = habits.stream()
                .map(habitMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(habitDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitDTO> getHabitById(@PathVariable int id) {
        Habit habit = habitService.getHabitById(id);
        if (habit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(habitMapper.toDTO(habit), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HabitDTO> createHabit(@RequestBody HabitDTO habitDTO) {
        Habit habit = habitMapper.toEntity(habitDTO);
        Habit createdHabit = habitService.createHabit(habit);
        return new ResponseEntity<>(habitMapper.toDTO(createdHabit), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitDTO> updateHabit(@PathVariable int id, @RequestBody HabitDTO habitDTO) {
        Habit updatedHabit = habitService.updateHabit(id, habitMapper.toEntity(habitDTO));
        if (updatedHabit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(habitMapper.toDTO(updatedHabit), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable int id) {
        Habit habit = habitService.getHabitById(id);
        if (habit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        habitService.deleteHabit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}