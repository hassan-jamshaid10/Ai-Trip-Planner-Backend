//package com.aitripplanner.AiTripPlanner.Controller;
//
//import com.aitripplanner.AiTripPlanner.Entites.User;
//import com.aitripplanner.AiTripPlanner.Services.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/users")
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserService userService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
//        return ResponseEntity.ok(userService.getUserById(id));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        return ResponseEntity.ok(userService.userRepository.findAll());
//    }
//
//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        return ResponseEntity.ok(userService.createUser(user));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
//        return ResponseEntity.ok(userService.updateUser(id, user));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }
//}
