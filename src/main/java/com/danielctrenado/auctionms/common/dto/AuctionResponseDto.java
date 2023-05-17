package com.danielctrenado.auctionms.common.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
public class AuctionResponseDto extends AuctionRequestDto {
    private Integer id;

    public AuctionResponseDto(LocalTime createdOn, BigDecimal initialPrice, LocalTime auctionStart,
                              LocalTime auctionEnd, String productName, String productDescription,
                              Integer categoryId, Integer id) {
        super(createdOn, initialPrice, auctionStart, auctionEnd, productName, productDescription, categoryId);
        this.id = id;
    }
}
