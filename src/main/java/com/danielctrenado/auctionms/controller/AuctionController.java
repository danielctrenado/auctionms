package com.danielctrenado.auctionms.controller;

import com.danielctrenado.auctionms.common.dto.AuctionDetailDto;
import com.danielctrenado.auctionms.common.dto.AuctionDto;
import com.danielctrenado.auctionms.common.dto.AuctionRequestDto;
import com.danielctrenado.auctionms.service.AuctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/auctions")
@RestController
public class AuctionController {

    private final AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping
    public ResponseEntity<AuctionDetailDto> createAuction(@RequestBody AuctionRequestDto auctionRequestDto) {
        try {
            log.info(">>> create auction starts");
            AuctionDetailDto auctionDetailDto = this.auctionService.createAuction(auctionRequestDto);
            return new ResponseEntity<>(auctionDetailDto, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error at createAuction:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<AuctionDto>> getAuctions() {
        try {
            log.info(">>> getAuctions");
            return new ResponseEntity<>(this.auctionService.getAuctions(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error at getAuctions:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
