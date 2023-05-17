package com.danielctrenado.auctionms.controller;

import com.danielctrenado.auctionms.common.dto.AuctionRequestDto;
import com.danielctrenado.auctionms.service.AuctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> createAuction(@RequestBody AuctionRequestDto auctionRequestDto) {
        log.info("An info message");
        log.warn("A warn message");
        log.debug("A debug message");
        log.trace("A trace message");
        log.error("A error message");

        this.auctionService.createAuction(auctionRequestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
