package com.fintech.paymentsystem.repository;

import com.fintech.paymentsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository extends JpaRepository <User,Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    // Check if a username already exists.
    boolean existsByUsername(String username);

    // Check if an email already exists.
    boolean existsByEmail(String email);

}
