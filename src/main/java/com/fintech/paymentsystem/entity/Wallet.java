package com.fintech.paymentsystem.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * Wallet entity - holds the user's balance and credit.
 * Each wallet belongs to exactly one user.
 */
@Entity
@Table(name = "wallets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Owner of this wallet (one-to-one). */
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    // Available balance (BigDecimal for precision)
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    // Blocked amount (pending transactions)
    @Column(name = "blocked_balance", precision = 19, scale = 2)
    private BigDecimal blockedBalance = BigDecimal.ZERO;

    // ---------- Credit fields (future) ----------
    // Maximum credit line
    @Column(name = "credit_limit", precision = 19, scale = 2)
    private BigDecimal creditLimit;

    // Amount of credit currently used.
    @Builder.Default
    @Column(name = "used_credit", precision = 19, scale = 2)
    private BigDecimal usedCredit = BigDecimal.ZERO;

    // Credit expiration date.
    @Column(name = "credit_expiry_date")
    private LocalDateTime creditExpiryDate;
    // -------------------------------------------

    /** Unique wallet number (e.g. W1726134000123456). */
    @Column(name = "wallet_number", unique = true, nullable = false)
    private String walletNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Transactions on this wallet (one-to-many)
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    // Sets timestamps and generates wallet number.
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (walletNumber == null) {
            walletNumber = generateWalletNumber();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Generates a unique wallet number.
    private String generateWalletNumber() {
        return "W" + System.currentTimeMillis() + (int) (Math.random() * 10000);
    }
}