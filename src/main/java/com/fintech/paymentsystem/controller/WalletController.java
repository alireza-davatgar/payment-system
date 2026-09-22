package com.fintech.paymentsystem.controller;

import com.fintech.paymentsystem.dto.request.DepositRequest;
import com.fintech.paymentsystem.dto.request.WithdrawRequest;
import com.fintech.paymentsystem.dto.response.WalletResponse;
import com.fintech.paymentsystem.mapper.WalletMapper;
import com.fintech.paymentsystem.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;
    private final WalletMapper walletMapper;

    // Get wallet by ID (for admin)
    @GetMapping("/{id}")
    public ResponseEntity<WalletResponse> getWalletById(@PathVariable Long id){
        var wallet=walletService.getWalletById(id);
        return ResponseEntity.ok(walletMapper.toResponse(wallet));
    }

    // Get wallet by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<WalletResponse> getWalletByUserId(@PathVariable Long userId){
        var wallet=walletService.getWalletByUserId(userId);
        return ResponseEntity.ok(walletMapper.toResponse(wallet));
    }

    // Get wallet by wallet number (for user)
    @GetMapping("/number/{walletNumber}")
    public ResponseEntity<WalletResponse> getWalletByWalletNumber(@PathVariable String walletNumber) {
        var wallet = walletService.getWalletByWalletNumber(walletNumber);
        return ResponseEntity.ok(walletMapper.toResponse(wallet));
    }

    // Deposit Money
    @PostMapping("{id}/deposit")
    public ResponseEntity<WalletResponse> deposit(@PathVariable Long id,
            @Valid @RequestBody DepositRequest request){
        var wallet=walletService.deposit(id,request.getAmount(),request.getDescription());
        return ResponseEntity.ok(walletMapper.toResponse(wallet));
    }

    // Withdraw money
     @PostMapping("/{id}/withdraw")
    public ResponseEntity<WalletResponse> withdraw(
             @PathVariable Long id,
             @Valid @RequestBody WithdrawRequest request){
        var wallet=walletService.withdraw(id,request.getAmount(), request.getDescription());
        return ResponseEntity.ok(walletMapper.toResponse(wallet));
     }

}
