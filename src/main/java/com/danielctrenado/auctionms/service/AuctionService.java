package com.danielctrenado.auctionms.service;

import com.danielctrenado.auctionms.common.dto.AuctionRequestDto;
import com.danielctrenado.auctionms.common.dto.AuctionResponseDto;

public interface AuctionService {

    AuctionResponseDto createAuction(AuctionRequestDto auctionRequestDto);
}
