package com.example.levelup.DTOs;

import java.util.List;

public record CreateUserDTO(
        int id,
        String firstName,
        String lastName,
        String username,
        String email,
        String password,
        String phone

) {
    public String getEmail() {
        return email;
    }

    public String setToken(String token) {
        return token;
    }
}
