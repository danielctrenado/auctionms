package com.danielctrenado.auctionms.service.impl;

import com.danielctrenado.auctionms.common.dto.BidDto;
import com.danielctrenado.auctionms.common.dto.BidPageDto;
import com.danielctrenado.auctionms.common.dto.BidRequestDto;
import com.danielctrenado.auctionms.persistence.entity.Auction;
import com.danielctrenado.auctionms.persistence.entity.Bid;
import com.danielctrenado.auctionms.persistence.repository.AuctionRepository;
import com.danielctrenado.auctionms.persistence.repository.BidRepository;
import com.danielctrenado.auctionms.service.BidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;

    private final AuctionRepository auctionRepository;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository, AuctionRepository auctionRepository) {
        this.bidRepository = bidRepository;
        this.auctionRepository = auctionRepository;
    }

    @Override
    public BidDto createBidForAuction(Integer auctionId, BidRequestDto bidRequestDto) {
        Auction auction = this.auctionRepository.findById(auctionId).orElseThrow();
        Bid bid = this.bidRepository.save(new Bid(LocalDateTime.now(), bidRequestDto.getAmount(), auction));
        log.info("[done] bid {} created for auction {}", bid.getId(), auctionId);
        return new BidDto(bid.getId(), bid.getCreatedOn(), bid.getAmount());
    }

    @Override
    public BidPageDto getBidsByAuction(Integer auctionId, Integer pageNumber, Integer pageSize,
                                       String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Bid> page = this.bidRepository.findAll(pageable);
        List<BidDto> lst = page.getContent().stream().map(bid ->
                new BidDto(bid.getId(), bid.getCreatedOn(), bid.getAmount())).toList();
        return new BidPageDto(lst, page.getNumber(), page.getSize(),
                page.getTotalElements(), page.getTotalPages(), page.isLast());
    }
}
