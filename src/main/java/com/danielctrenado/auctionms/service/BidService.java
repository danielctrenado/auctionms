package com.danielctrenado.auctionms.service;

import com.danielctrenado.auctionms.common.dto.BidDto;
import com.danielctrenado.auctionms.common.dto.BidPageDto;
import com.danielctrenado.auctionms.common.dto.BidRequestDto;

import java.util.List;

public interface BidService {

    BidDto createBidForAuction(Integer auctionId, BidRequestDto bidRequestDto);

    BidPageDto getBidsByAuction(Integer auctionId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
}
