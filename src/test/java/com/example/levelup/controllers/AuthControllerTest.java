package com.example.levelup.controllers;

import com.example.levelup.DTOs.CreateUserDTO;
import com.example.levelup.mappers.UserMapper;
import com.example.levelup.models.User;
import com.example.levelup.repositories.UserRepository;
import com.example.levelup.security.JwtAuthenticationFilter;
import com.example.levelup.services.JwtService;
import com.example.levelup.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @MockitoBean
    private AuthenticationManager authenticationManager;



    @Test
    void testRegisterUser_Success() throws Exception {
        CreateUserDTO requestDTO = new CreateUserDTO(0, "Albion", "Stublla", "@albion", "a@gmail.com", "12345678", "044123456");

        User userEntity = new User();
        userEntity.setFirstName("Albion");
        userEntity.setLastName("Stublla");
        userEntity.setUsername("@albion");
        userEntity.setEmail("a@gmail.com");
        userEntity.setPassword("12345678");
        userEntity.setPhone("044123456");

        User savedUser = new User();
        savedUser.setId(1);
        savedUser.setFirstName("Albion");
        savedUser.setLastName("Stublla");
        savedUser.setUsername("@albion");
        savedUser.setEmail("a@gmail.com");
        savedUser.setPassword("12345678");
        savedUser.setPhone("044123456");

        CreateUserDTO responseDTO = new CreateUserDTO(1, "Albion", "Stublla", "@albion", "a@gmail.com", "12345678", "044123456");

        try (MockedStatic<UserMapper> mockedMapper = Mockito.mockStatic(UserMapper.class)) {
            // Mock the static methods
            mockedMapper.when(() -> UserMapper.toCreateEntity(requestDTO)).thenReturn(userEntity);
            mockedMapper.when(() -> UserMapper.toCreateDTO(savedUser)).thenReturn(responseDTO);

            Mockito.when(userService.existsByEmail(requestDTO.getEmail())).thenReturn(false);
            Mockito.when(passwordEncoder.encode("12345678")).thenReturn("encodedPassword");
            Mockito.when(userService.createUser(userEntity)).thenReturn(savedUser);

            mockMvc.perform(post("/api/auth/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestDTO)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.username").value("@albion"))
                    .andExpect(jsonPath("$.email").value("a@gmail.com"));
        }
    }


}
