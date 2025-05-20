
package com.example.levelup.mappers;

import com.example.levelup.DTOs.CreateUserDTO;
import com.example.levelup.DTOs.HabitDTO;
import com.example.levelup.models.User;
import com.example.levelup.models.Habit;
import com.example.levelup.DTOs.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static User toEntity(UserDTO dto) {
        User user = new User();

        user.setId(dto.id());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());

        return user;
    }

    public static User toCreateEntity(CreateUserDTO dto) {
        User user = new User();

        user.setId(dto.id());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setPhone(dto.phone());


        return user;
    }

    public static CreateUserDTO toCreateDTO(User user) {
        return new CreateUserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone()

        );
    }

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                null
        );
    }

    public static UserDTO toDTOWithHabits(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                toHabitDTOs(user.getHabits())
        );
    }
        public static List<Habit> toHabits(List<HabitDTO> dtos) {
            return dtos.stream()
                    .map(dto -> {
                        Habit habit = new Habit();
                        habit.setId(dto.id());
                        habit.setTitle(dto.title());
                        habit.setDescription(dto.description());
                        return habit;
                    })
                    .collect(Collectors.toList());
        }
        public static List<HabitDTO> toHabitDTOs(List<Habit> habits) {
            return habits.stream()
                    .map(habit -> new HabitDTO(
                            habit.getId(),
                            habit.getTitle(),
                            habit.getDescription(),
                            habit.getStartDate(),
                            habit.getEndDate(),
                            habit.getFrequency(),
                            habit.getTimes(),
                            habit.getUser() != null ? habit.getUser().getId() : 0,
                            habit.getCategory() != null ? habit.getCategory().getId() : 0
                    ))
                    .collect(Collectors.toList());
        }
}

