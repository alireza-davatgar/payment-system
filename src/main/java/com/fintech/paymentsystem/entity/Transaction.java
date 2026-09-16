package com.fintech.paymentsystem.entity;

import com.fintech.paymentsystem.entity.enums.TransactionStatus;
import com.fintech.paymentsystem.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * Transaction entity - represents a financial transaction.
 * Transactions are immutable; they are never updated.
 */
@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Unique transaction ID (e.g. TXN-1726134000-5678). */
    @Column(name = "transaction_id", unique = true, nullable = false)
    private String transactionId;

    // Wallet on which this transaction was performed (many-to-one)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    //Optional order (null for deposits/withdrawals)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    // Transaction amount
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    // Wallet balance after this transaction
    @Column(name = "balance_after", nullable = false, precision = 19, scale = 2)
    private BigDecimal balanceAfter;

    // CREDIT (deposit) or DEBIT (withdrawal)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    // Transaction status (default: PENDING)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status = TransactionStatus.PENDING;

    // Optional description
    @Column(length = 500)
    private String description;

    // Optional external reference (e.g. bank tracking code).
    @Column(name = "reference_number")
    private String referenceNumber;

    // Transactions are immutable — no updatedAt field.
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Sets timestamp and generates transaction ID
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (transactionId == null) {
            transactionId = generateTransactionId();
        }
    }

    // Generates a unique transaction ID.
    private String generateTransactionId() {
        return "TXN-" + System.currentTimeMillis() + "-" + (int) (Math.random() * 10000);
    }
}