package com.danielctrenado.auctionms.service.impl;

import com.danielctrenado.auctionms.common.dto.*;
import com.danielctrenado.auctionms.service.AuctionService;
import com.danielctrenado.auctionms.service.BidService;
import com.danielctrenado.auctionms.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class BidServiceImplTest {

    private final BidService bidService;

    private final AuctionService auctionService;

    private final CategoryService categoryService;

    private Integer auctionId;

    private static final Integer BIDS_CREATED = 15;

    @Autowired
    public BidServiceImplTest(BidService bidService, AuctionService auctionService, CategoryService categoryService) {
        this.bidService = bidService;
        this.auctionService = auctionService;
        this.categoryService = categoryService;
    }

    @Test
    void createBidForAuction_ok() {
        CategoryDto categoryDto = this.categoryService.createCategory(new CategoryRequestDto("category"));
        AuctionDetailDto auctionDetailDto = this.auctionService.createAuction(
                new AuctionRequestDto(BigDecimal.valueOf(5.50), LocalDateTime.now(), LocalDateTime.now().plusHours(72),
                        "prodNameForBid", "DescProdForBid", categoryDto.getId()));
        this.auctionId = auctionDetailDto.getId();

        for (int counter = 0; counter < BIDS_CREATED; counter++) {
            BigDecimal amount = BigDecimal.valueOf(6.5 + counter);
            BidDto bidDto = this.bidService.createBidForAuction(auctionDetailDto.getId(),
                    new BidRequestDto(amount));
            assertNotNull(bidDto.getId());
            assertEquals(amount, bidDto.getAmount());
        }
    }

    @Test
    void getBidsByAuction_ok() {
        int pageNumber = 0, pageSize = 5;
        String sortBy = "createdOn";
        List<String> sortOrders = List.of("DESC", "ASC");

        for (String sortOrder : sortOrders) {
            BidPageDto bidPageDto = this.bidService.getBidsByAuction(this.auctionId, pageNumber, pageSize,
                    sortBy, sortOrder);
            assertEquals(pageSize, bidPageDto.getPageSize());
            assertEquals(pageNumber, bidPageDto.getPageNumber());
            assertFalse(bidPageDto.isLast());
            assertEquals(BIDS_CREATED / pageSize, bidPageDto.getTotalPages());

            Set<BidDto> bidDtos;
            log.info("Order verification:" + sortOrder);
            if (sortOrder.equals("DESC")) {
                bidDtos = new TreeSet<>((BidDto b1, BidDto b2) -> b2.getCreatedOn().compareTo(b1.getCreatedOn()));
            } else {
                bidDtos = new TreeSet<>(Comparator.comparing(BidDto::getCreatedOn));
            }
            bidDtos.addAll(bidPageDto.getBids());
            assertEquals(bidDtos.stream().toList(), bidPageDto.getBids());
        }

    }

}
