package com.danielctrenado.auctionms.common.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidRequestDto {
    @NotNull
    @Digits(integer = 6, fraction = 2)
    private BigDecimal amount;
}
