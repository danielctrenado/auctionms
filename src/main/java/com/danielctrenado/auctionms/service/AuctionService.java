package com.danielctrenado.auctionms.service;

import com.danielctrenado.auctionms.common.dto.AuctionDetailDto;
import com.danielctrenado.auctionms.common.dto.AuctionDto;
import com.danielctrenado.auctionms.common.dto.AuctionRequestDto;

import java.util.List;

public interface AuctionService {

    AuctionDetailDto createAuction(AuctionRequestDto auctionRequestDto);

    List<AuctionDto> getAuctions();

    AuctionDto getAuctionById(Integer id);
}
