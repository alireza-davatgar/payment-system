package com.fintech.paymentsystem.service;

import com.fintech.paymentsystem.entity.Transaction;
import com.fintech.paymentsystem.entity.Wallet;
import com.fintech.paymentsystem.entity.enums.TransactionStatus;
import com.fintech.paymentsystem.entity.enums.TransactionType;
import com.fintech.paymentsystem.repository.TransactionRepository;
import com.fintech.paymentsystem.repository.UserRepository;
import com.fintech.paymentsystem.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService
{
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public Wallet getWalletById(Long id){
        return walletRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Wallet not found by id:"+ id));
    }

    // Get wallet by user ID
    public Wallet getWalletByUserId(Long userId) {
        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found for user id: " + userId));
    }

    // Get wallet by wallet number
    public Wallet getWalletByWalletNumber(String walletNumber) {
        return walletRepository.findByWalletNumber(walletNumber)
                .orElseThrow(() -> new RuntimeException("Wallet not found with number: " + walletNumber));
    }

    // Deposit money into wallet
    @Transactional
    public Wallet deposit(Long walletId, BigDecimal amount,String description){
        Wallet wallet=getWalletById(walletId);

        BigDecimal newBalance=wallet.getBalance().add(amount);
        wallet.setBalance(newBalance);

        Transaction transaction =Transaction.builder()
                .wallet(wallet)
                .amount(amount)
                .balanceAfter(newBalance)
                .type(TransactionType.CREDIT)
                .status(TransactionStatus.SUCCESS)
                .description(description !=null ? description :"Deposit")
                .build();
        transactionRepository.save(transaction);

        return walletRepository.save(wallet);
    }

    // Withdraw money from wallet
    @Transactional
    public Wallet withdraw(Long walletId,BigDecimal amount,String description ){
        Wallet wallet=getWalletById(walletId);

        if (wallet.getBalance().compareTo(amount)<0){
            throw new RuntimeException("Insufficient balance");
        }
        BigDecimal newBalance=wallet.getBalance().subtract(amount);

        wallet.setBalance(newBalance);

        Transaction transaction= Transaction.builder()
                .wallet(wallet)
                .amount(amount)
                .balanceAfter(newBalance)
                .type(TransactionType.DEBIT)
                .status(TransactionStatus.SUCCESS)
                .description(description !=null ? description :"Withdraw")
                .build();
        transactionRepository.save(transaction);
        return walletRepository.save(wallet);
    }

    public List<Transaction> getTransactions(Long walletId ){
        Wallet wallet=getWalletById(walletId);
        return transactionRepository.findByWalletOrderByCreatedAtDesc(wallet);
    }
}
