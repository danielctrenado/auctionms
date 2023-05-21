package com.danielctrenado.auctionms.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BidDto extends BidRequestDto {
    private Integer id;
    private LocalDateTime createdOn;

    public BidDto(Integer id, LocalDateTime createdOn, BigDecimal amount) {
        super(amount);
        this.id = id;
        this.createdOn = createdOn;
    }

}
