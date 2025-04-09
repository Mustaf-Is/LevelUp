package com.example.levelup.DTOs;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;

}