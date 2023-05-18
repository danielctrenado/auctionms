package com.danielctrenado.auctionms.common.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AuctionDetailDto extends AuctionRequestDto {
    private Integer id;
    private LocalDateTime createdOn;

    public AuctionDetailDto(BigDecimal initialPrice, LocalDateTime auctionStart,
                            LocalDateTime auctionEnd, String productName, String productDescription,
                            Integer categoryId, Integer id, LocalDateTime createdOn) {
        super(initialPrice, auctionStart, auctionEnd, productName, productDescription, categoryId);
        this.id = id;
        this.createdOn = createdOn;
    }
}
