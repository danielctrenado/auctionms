package com.danielctrenado.auctionms.service.impl;

import com.danielctrenado.auctionms.common.dto.AuctionRequestDto;
import com.danielctrenado.auctionms.common.dto.AuctionResponseDto;
import com.danielctrenado.auctionms.common.dto.CategoryResponseDto;
import com.danielctrenado.auctionms.service.AuctionService;
import com.danielctrenado.auctionms.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuctionServiceImplTest {

    private final AuctionService auctionService;

    private final CategoryService categoryService;

    @Autowired
    public AuctionServiceImplTest(AuctionService auctionService, CategoryService categoryService) {
        this.auctionService = auctionService;
        this.categoryService = categoryService;
    }

    @Test
    void verify_createAuction() {
        CategoryResponseDto categoryResponseDto = this.categoryService.createCategory("category1");

        String productName = "name01", productDescription = "desc01";
        BigDecimal initialPrice = BigDecimal.valueOf(15.50);
        AuctionRequestDto auctionRequestDto = new AuctionRequestDto(LocalTime.now(), initialPrice,
                LocalTime.now(), LocalTime.now().plusHours(72), productName, productDescription,
                categoryResponseDto.getId());

        AuctionResponseDto response = this.auctionService.createAuction(auctionRequestDto);
        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(productName, response.getProductName());
        assertEquals(productDescription, response.getProductDescription());
        assertEquals(initialPrice, response.getInitialPrice());
    }
}
