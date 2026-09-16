package com.fintech.paymentsystem.repository;

import com.fintech.paymentsystem.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {

    // Find a wallet by its unique wallet number.
    Optional<Wallet> findByWalletNumber(String walletNumber);

    // Find a wallet by the owner's user ID.
    Optional<Wallet> findByUserId(Long userId);

}
