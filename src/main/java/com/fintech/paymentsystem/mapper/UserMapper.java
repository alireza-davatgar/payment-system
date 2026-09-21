package com.fintech.paymentsystem.mapper;

import com.fintech.paymentsystem.dto.request.UserRequest;
import com.fintech.paymentsystem.dto.response.UserResponse;
import com.fintech.paymentsystem.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // DTO → Entity
    public User toEntity(UserRequest request){
        if (request ==null) return null;
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    // Entity → DTO
    public UserResponse toResponse(User user){
        if(user==null)return null;

        return  UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .enabled((user.isEnabled()))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
