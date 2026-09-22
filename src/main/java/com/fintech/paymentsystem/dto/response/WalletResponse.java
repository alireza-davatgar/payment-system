package com.fintech.paymentsystem.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalletResponse {

    private Long id;
    private String walletNumber;
    private Long uerId;
    private String userFullName;
    private BigDecimal balance;
    private BigDecimal blockedBalance;
    private BigDecimal creditLimit;
    private BigDecimal usedCredit;
    private BigDecimal availableCredit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
