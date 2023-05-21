package com.danielctrenado.auctionms.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuctionRequestDto {
    @NotNull(message = "initialPrice is required")
    @Digits(integer = 6, fraction = 2)
    private BigDecimal initialPrice;

    @NotNull(message = "auctionStart is required")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auctionStart;

    @NotNull(message = "auctionEnd is required")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auctionEnd;

    @NotEmpty(message = "productName is required")
    private String productName;

    @NotEmpty(message = "productDescription is required")
    private String productDescription;

    @NotNull(message = "categoryId is required")
    private Integer categoryId;

}
