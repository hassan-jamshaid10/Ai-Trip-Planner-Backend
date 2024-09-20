//package com.aitripplanner.AiTripPlanner;
//
//
//
//import com.aitripplanner.AiTripPlanner.Controller.UserController;
//import com.aitripplanner.AiTripPlanner.Entites.User;
//import com.aitripplanner.AiTripPlanner.Services.UserService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private UserController userController;
//
//    @Test
//    public void testCreateUser() throws Exception {
//        User user = new User();
//        when(userService.createUser(any(User.class))).thenReturn(user);
//
//        mockMvc.perform(post("/api/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"username\": \"testuser\", \"passwordHash\": \"hashedpassword\"}"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.username").value("testuser"));
//    }
//}
