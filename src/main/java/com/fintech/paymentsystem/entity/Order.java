package com.fintech.paymentsystem.entity;
import com.fintech.paymentsystem.entity.enums.OrderStatus;
import com.fintech.paymentsystem.entity.enums.OrderType;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Order entity - represents a payment request.
 * Each order belongs to a user and may have multiple transactions.
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique order number (e.g. ORD-1726134000-1234)
    @Column(name = "order_number", unique = true, nullable = false)
    private String orderNumber;

    // User who placed this order (many-to-one)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Optional description.
    @Column(length = 500)
    private String description;

    // Total amount
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    // Order status (default: PENDING)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    // Order type (default: PAYMENT)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType type = OrderType.PAYMENT;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /// Transactions related to this order (one-to-many)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    /// Sets timestamps and generates order number.
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (orderNumber == null) {
            orderNumber = generateOrderNumber();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    /** Generates a unique order number. */
    private String generateOrderNumber() {
        return "ORD-" + System.currentTimeMillis() + "-" + (int) (Math.random() * 10000);
    }
}