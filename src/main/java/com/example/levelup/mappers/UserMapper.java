
package com.example.levelup.mappers;

import com.example.levelup.models.User;
import com.example.levelup.models.Habit;
import com.example.levelup.DTOs.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserMapper {

    public static User toEntity(UserDTO dto) {
        User user = new User();

        user.setId(dto.id());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setPhone(dto.phone());
        user.setHabits(dto.habits());

        return user;
    }

    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getPassword(),
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
                user.getPassword(),
                user.getPhone(),
                user.getHabits()
        );
    }
}

