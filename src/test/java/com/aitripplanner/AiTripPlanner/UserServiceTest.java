package com.aitripplanner.AiTripPlanner;

import com.aitripplanner.AiTripPlanner.Entites.User;
import com.aitripplanner.AiTripPlanner.Repository.UserRepository;
import com.aitripplanner.AiTripPlanner.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password");
    }

    @Test
    void registerNewUser_shouldSaveUser() {
        // Arrange
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User registeredUser = userService.registerNewUser("testuser", "testuser@example.com", "password");

        // Assert
        assertNotNull(registeredUser);
        assertEquals("testuser", registeredUser.getUsername());
        assertEquals("testuser@example.com", registeredUser.getEmail());
        assertEquals("encodedPassword", registeredUser.getPassword());
        verify(passwordEncoder).encode("password");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void findByUsername_shouldReturnUser() {
        // Arrange
        when(userRepository.findByUsername("testuser")).thenReturn(java.util.Optional.of(user));

        // Act
        User foundUser = userService.findByUsername("testuser");

        // Assert
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
        verify(userRepository).findByUsername("testuser");
    }

    @Test
    void findByUsername_shouldThrowException_whenUserNotFound() {
        // Arrange
        when(userRepository.findByUsername("unknownuser")).thenReturn(java.util.Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.findByUsername("unknownuser");
        });
        assertEquals("User not found with username: unknownuser", exception.getMessage());
    }
}
