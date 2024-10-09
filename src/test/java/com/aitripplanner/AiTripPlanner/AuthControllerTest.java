package com.aitripplanner.AiTripPlanner;
import com.aitripplanner.AiTripPlanner.Authentication.AuthController;
import com.aitripplanner.AiTripPlanner.Authentication.Request.LoginRequest;
import com.aitripplanner.AiTripPlanner.Authentication.Request.RegisterRequest;
import com.aitripplanner.AiTripPlanner.Authentication.Response.AuthResponse;
import com.aitripplanner.AiTripPlanner.Services.UserService;
import com.aitripplanner.AiTripPlanner.Util.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserService userService;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private UserDetails userDetails;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AuthController authController;

    private LoginRequest loginRequest;
    private RegisterRequest registerRequest;
    private String token;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("testpassword");

        registerRequest = new RegisterRequest();
        registerRequest.setUsername("testuser");
        registerRequest.setEmail("testuser@example.com");
        registerRequest.setPassword("testpassword");

        token = "fake-jwt-token";
    }

    @Test
    public void testAuthenticateUser_Success() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtTokenUtil.generateToken(userDetails)).thenReturn(token);

        ResponseEntity<AuthResponse> response = authController.authenticateUser(loginRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, response.getBody().getToken());
        assertEquals("testuser", response.getBody().getUsername());
    }

    @Test
    public void testRegisterUser_Success() {
        doNothing().when(userService).registerNewUser(anyString(), anyString(), anyString());

        ResponseEntity<String> response = authController.registerUser(registerRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());
    }
}

