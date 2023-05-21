package com.danielctrenado.auctionms.controller;

import com.danielctrenado.auctionms.common.dto.BidDto;
import com.danielctrenado.auctionms.common.dto.BidPageDto;
import com.danielctrenado.auctionms.common.dto.BidRequestDto;
import com.danielctrenado.auctionms.service.BidService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/v1/auctions")
@RestController
public class BidController {

    private final BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping("/{auctionId}/bids")
    public ResponseEntity<BidDto> createBidForAuction(@PathVariable("auctionId") Integer auctionId,
                                                      @RequestBody @Valid BidRequestDto bidRequestDto) {
        try {
            log.info(">>> create bid for auction starts");
            return new ResponseEntity<>(this.bidService.createBidForAuction(auctionId, bidRequestDto), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error at createBidForAuction:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{auctionId}/bids")
    public ResponseEntity<BidPageDto> getBidsByAuction(@PathVariable("auctionId") Integer auctionId,
                                                       @RequestParam(value = "pageNumber") Integer pageNumber,
                                                       @RequestParam(value = "pageSize") Integer pageSize,
                                                       @RequestParam(value = "sortBy") String sortBy,
                                                       @RequestParam(value = "sortOrder") String sortOrder) {
        try {
            log.info(">>> getBidsByAuction starts");
            return new ResponseEntity<>(this.bidService.getBidsByAuction(auctionId, pageNumber,
                    pageSize, sortBy, sortOrder), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error at getBidsByAuction:", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
