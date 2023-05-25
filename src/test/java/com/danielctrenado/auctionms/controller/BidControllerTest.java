package com.danielctrenado.auctionms.controller;

import com.danielctrenado.auctionms.common.dto.BidDto;
import com.danielctrenado.auctionms.common.dto.BidPageDto;
import com.danielctrenado.auctionms.common.dto.BidRequestDto;
import com.danielctrenado.auctionms.service.BidService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
public class BidControllerTest {

    private static final String BIDS_URI = "/api/v1/auctions/{auctionId}/bids";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidService bidService;

    @Test
    void getBidsByAuction_ok() throws Exception {
        int auctionId = 1, pageNumber = 0, pageSize = 5;
        String sortBy = "amount", sortOrder = "ASC";
        final String getBidsByAuctionIdUri = BIDS_URI.replace("{auctionId}", String.valueOf(auctionId));

        when(this.bidService.getBidsByAuction(auctionId, pageNumber, pageSize, sortBy, sortOrder))
                .thenReturn(new BidPageDto(List.of(), pageNumber, pageSize, 0L, pageSize, true));

        this.mockMvc.perform(MockMvcRequestBuilders.get(getBidsByAuctionIdUri)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .param("pageNumber", String.valueOf(pageNumber))
                        .param("pageSize", String.valueOf(pageSize))
                        .param("sortBy", sortBy)
                        .param("sortOrder", sortOrder))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalItems").value(0));
    }

    @Test
    void getBidsByAuction_serverError() throws Exception {
        int auctionId = 5, pageNumber = 0, pageSize = 5;
        String sortBy = "amount", sortOrder = "ASC";
        final String getBidsByAuctionIdUri = BIDS_URI.replace("{auctionId}", String.valueOf(auctionId));
        when(this.bidService.getBidsByAuction(auctionId, pageNumber, pageSize, sortBy, sortOrder))
                .thenThrow(new RuntimeException("Error"));

        this.mockMvc.perform(MockMvcRequestBuilders.get(getBidsByAuctionIdUri)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .param("pageNumber", String.valueOf(pageNumber))
                        .param("pageSize", String.valueOf(pageSize))
                        .param("sortBy", sortBy)
                        .param("sortOrder", sortOrder))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void createBidForAuction_ok() throws Exception {
        int auctionId = 1;
        BigDecimal amount = BigDecimal.valueOf(20.5);
        BidRequestDto bidRequestDto = new BidRequestDto(amount);
        final String createBidForAuctionUri = BIDS_URI.replace("{auctionId}", String.valueOf(auctionId));

        when(this.bidService.createBidForAuction(auctionId, bidRequestDto))
                .thenReturn(new BidDto(5, LocalDateTime.now(), amount));

        this.mockMvc.perform(MockMvcRequestBuilders.post(createBidForAuctionUri)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bidRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(amount));
    }

    @Test
    void createBidForAuction_serverError() throws Exception {
        int auctionId = 5;
        BigDecimal amount = BigDecimal.valueOf(25.50);
        BidRequestDto bidRequestDto = new BidRequestDto(amount);
        final String createBidForAuctionUri = BIDS_URI.replace("{auctionId}", String.valueOf(auctionId));

        when(this.bidService.createBidForAuction(auctionId, bidRequestDto)).thenThrow(new RuntimeException("Error"));

        this.mockMvc.perform(MockMvcRequestBuilders.post(createBidForAuctionUri)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bidRequestDto)))
                .andExpect(status().is5xxServerError());
    }


}
