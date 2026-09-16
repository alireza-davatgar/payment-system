package com.fintech.paymentsystem.repository;

import com.fintech.paymentsystem.entity.Transaction;
import com.fintech.paymentsystem.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findByWalletOrderByCreatedAtDesc(Wallet wallet);
}
