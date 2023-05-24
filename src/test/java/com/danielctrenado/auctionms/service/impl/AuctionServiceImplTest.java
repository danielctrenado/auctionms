package com.danielctrenado.auctionms.service.impl;

import com.danielctrenado.auctionms.common.dto.AuctionDetailDto;
import com.danielctrenado.auctionms.common.dto.AuctionDto;
import com.danielctrenado.auctionms.common.dto.AuctionRequestDto;
import com.danielctrenado.auctionms.common.dto.CategoryRequestDto;
import com.danielctrenado.auctionms.common.dto.CategoryDto;
import com.danielctrenado.auctionms.service.AuctionService;
import com.danielctrenado.auctionms.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
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
    void createAuction_ok() {
        String itemName = "name01", itemDesc = "desc01";
        BigDecimal initialPrice = BigDecimal.valueOf(15.50);
        AuctionDetailDto response = this.createAuction("category1", itemName, itemDesc, initialPrice,
                LocalDateTime.now(), LocalDateTime.now().plusHours(72));

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(itemName, response.getProductName());
        assertEquals(itemDesc, response.getProductDescription());
        assertEquals(initialPrice, response.getInitialPrice());
    }

    @Test
    void getAuctionById_ok() {
        AuctionDetailDto auctionDetailDto = this.createAuction("Books", "The little prince",
                "Desc", BigDecimal.valueOf(20.00), LocalDateTime.now(), LocalDateTime.now().plusHours(72));
        AuctionDto auctionDto = this.auctionService.getAuctionById(auctionDetailDto.getId());
        assertEquals(auctionDetailDto.getId(), auctionDto.getId());
        assertEquals(auctionDetailDto.getAuctionStart(), auctionDto.getAuctionStart());
        assertEquals(auctionDetailDto.getAuctionEnd(), auctionDto.getAuctionEnd());
        assertEquals(auctionDetailDto.getProductName(), auctionDto.getProductName());
    }

    @Test
    void getAuctions_ok() {
        int numberOfAuctions = 5;
        for (int counter = 0; counter < numberOfAuctions; counter++) {
            this.createAuction("category" + counter, "name" + counter, "desc" + counter,
                    BigDecimal.valueOf(counter+1), LocalDateTime.now(), LocalDateTime.now().plusHours(72));
        }

        List<AuctionDto> auctions = this.auctionService.getAuctions();
        assertEquals(numberOfAuctions + 2, auctions.size());
    }

    private AuctionDetailDto createAuction(String category, String itemName, String itemDesc,
                                           BigDecimal initialPrice, LocalDateTime auctionStart,
                                           LocalDateTime auctionEnd) {
        CategoryDto categoryDto = this.categoryService.createCategory(new CategoryRequestDto(category));
        AuctionRequestDto auctionRequestDto = new AuctionRequestDto(initialPrice,
                auctionStart, auctionEnd, itemName, itemDesc, categoryDto.getId());

        return this.auctionService.createAuction(auctionRequestDto);
    }
}
