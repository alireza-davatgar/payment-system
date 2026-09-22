package com.fintech.paymentsystem.controller;
import com.fintech.paymentsystem.dto.request.UserRequest;
import com.fintech.paymentsystem.dto.response.UserResponse;
import com.fintech.paymentsystem.entity.User;
import com.fintech.paymentsystem.service.UserService;
import com.fintech.paymentsystem.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// REST controller for managing users.
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    // Create a new user.
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request){
        var user =userMapper.toEntity(request);
        var createdUser=userService.createUser(user);
        return new ResponseEntity<>(userMapper.toResponse(createdUser), HttpStatus.CREATED);
    }

    // Get all users.
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        var users=userService.getAllUsers()
                .stream()
                .map(userMapper::toResponse)
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        var user =userService.getUserById(id);
        return ResponseEntity.ok(userMapper.toResponse(user));
    }

    // Get a user by username.
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable  String username){
        var user =userService.getUserByUsername(username);
        return ResponseEntity.ok(userMapper.toResponse(user));
    }


}
