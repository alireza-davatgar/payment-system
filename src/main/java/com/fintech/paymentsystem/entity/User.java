package com.fintech.paymentsystem.entity;

import com.fintech.paymentsystem.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * User entity - represents a user in the payment system.
 * Each user has one wallet and can have multiple orders.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique username used for login
    @Column(unique = true, nullable = false, length = 50)
    private String username;

    //Unique email used for login and notifications
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    // Hashed password (never stored in plain text)
    @Column(nullable = false)
    private String password;

    // User's full name
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    // 11-digit Iranian mobile number
    @Column(name = "phone_number", nullable = false, length = 11)
    private String phoneNumber;

    // User role (USER or ADMIN)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.USER;

    // Disabled users cannot log in
    @Column(nullable = false)
    private boolean enabled = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /** Wallet associated with this user (one-to-one). */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Wallet wallet;

    // Orders placed by this user (one-to-many)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    // Sets timestamps before persisting.
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // Updates timestamp before updating
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}