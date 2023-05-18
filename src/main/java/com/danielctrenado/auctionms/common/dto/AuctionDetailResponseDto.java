package com.danielctrenado.auctionms.common.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class AuctionDetailResponseDto extends AuctionRequestDto {
    private Integer id;

    public AuctionDetailResponseDto(LocalDateTime createdOn, BigDecimal initialPrice, LocalDateTime auctionStart,
                                    LocalDateTime auctionEnd, String productName, String productDescription,
                                    Integer categoryId, Integer id) {
        super(createdOn, initialPrice, auctionStart, auctionEnd, productName, productDescription, categoryId);
        this.id = id;
    }
}
