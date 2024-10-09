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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initializes @Mock and @InjectMocks
    }

    @Test
    void testRegisterNewUser() {
        // Arrange
        String username = "testUser";
        String email = "test@example.com";
        String password = "password";
        String encodedPassword = "encodedPassword";

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(encodedPassword);

        // Mock password encoder behavior
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        // Mock repository save behavior
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User registeredUser = userService.registerNewUser(username, email, password);

        // Assert
        assertNotNull(registeredUser);
        assertEquals(username, registeredUser.getUsername());
        assertEquals(email, registeredUser.getEmail());
        assertEquals(encodedPassword, registeredUser.getPassword());

        // Verify the interactions
        verify(passwordEncoder, times(1)).encode(password);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testFindByUsername_UserFound() {
        // Arrange
        String username = "testUser";
        User user = new User();
        user.setUsername(username);

        // Mock repository findByUsername behavior
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        User foundUser = userService.findByUsername(username);

        // Assert
        assertNotNull(foundUser);
        assertEquals(username, foundUser.getUsername());

        // Verify the interactions
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testFindByUsername_UserNotFound() {
        // Arrange
        String username = "nonExistentUser";

        // Mock repository findByUsername behavior
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.findByUsername(username);
        });

        assertEquals("User not found with username: " + username, exception.getMessage());

        // Verify the interactions
        verify(userRepository, times(1)).findByUsername(username);
    }
}
