package com.danielctrenado.auctionms.service;

import com.danielctrenado.auctionms.common.dto.AuctionDetailResponseDto;
import com.danielctrenado.auctionms.common.dto.AuctionDto;
import com.danielctrenado.auctionms.common.dto.AuctionRequestDto;

import java.util.List;

public interface AuctionService {

    AuctionDetailResponseDto createAuction(AuctionRequestDto auctionRequestDto);
    List<AuctionDto> getAuctions();
}
