package com.fintech.paymentsystem.controller;
import com.fintech.paymentsystem.entity.User;
import com.fintech.paymentsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// REST controller for managing users.
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Create a new user.
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser=userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Get all users.
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Get a user by username.
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable  String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }
}
