package com.danielctrenado.auctionms.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuctionRequestDto {
    private LocalTime createdOn;
    private BigDecimal initialPrice;
    private LocalTime auctionStart;
    private LocalTime auctionEnd;

    private String productName;
    private String productDescription;

    private Integer categoryId;

}
