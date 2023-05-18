package com.danielctrenado.auctionms.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDto {
    private Integer id;
    private BigDecimal initialPrice;
    private LocalDateTime auctionStart;
    private LocalDateTime auctionEnd;
    private String productName;
}
