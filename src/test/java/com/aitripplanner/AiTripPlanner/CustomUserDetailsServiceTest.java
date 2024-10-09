package com.aitripplanner.AiTripPlanner;

import com.aitripplanner.AiTripPlanner.Entites.User;
import com.aitripplanner.AiTripPlanner.Repository.UserRepository;
import com.aitripplanner.AiTripPlanner.Services.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_UserExists() {
        String username = "testUser";
        User user = new User();
        user.setUsername(username);
        user.setPassword("password123"); // Use a hashed password in real scenarios

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        var userDetails = customUserDetailsService.loadUserByUsername(username);
        assertEquals(username, userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        String username = "nonExistentUser";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername(username);
        });
        assertEquals("User not found with username: " + username, exception.getMessage());
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = customUserDetailsService.save(user);
        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(user);
    }
}
