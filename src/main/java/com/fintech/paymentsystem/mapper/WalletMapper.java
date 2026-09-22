package com.fintech.paymentsystem.mapper;

import com.fintech.paymentsystem.dto.response.WalletResponse;
import com.fintech.paymentsystem.entity.Wallet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WalletMapper {

    public WalletResponse toResponse(Wallet wallet){
        if(wallet == null) return null;
        BigDecimal availableCredit=BigDecimal.ZERO;

        if (wallet.getCreditLimit()!= null){
            availableCredit=wallet.getCreditLimit()
                    .subtract(wallet.getUsedCredit() !=null ? wallet.getUsedCredit() :BigDecimal.ZERO);
        }
        return WalletResponse.builder()
                .id(wallet.getId())
                .walletNumber(wallet.getWalletNumber())
                .uerId(wallet.getUser().getId())
                .userFullName(wallet.getUser().getFullName())
                .balance(wallet.getBalance())
                .blockedBalance(wallet.getBlockedBalance())
                .creditLimit(wallet.getCreditLimit())
                .usedCredit(wallet.getUsedCredit())
                .availableCredit(availableCredit)
                .createdAt(wallet.getCreatedAt())
                .updatedAt(wallet.getUpdatedAt())
                .build();
    }


}
