package com.fintech.paymentsystem.repository;

import com.fintech.paymentsystem.entity.Order;
import com.fintech.paymentsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Repository for managing Order entities.
public interface OrderRepository extends JpaRepository<Order,Long> {

    // Find all orders placed by a specific user.
    List<Order> findByUser(User user);

    // Find an order by its unique order number.
    Optional<Order> findByOrderNumber(String orderNumber);
}
