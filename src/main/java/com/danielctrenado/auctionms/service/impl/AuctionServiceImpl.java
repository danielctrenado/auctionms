package com.danielctrenado.auctionms.service.impl;

import com.danielctrenado.auctionms.common.dto.AuctionDetailDto;
import com.danielctrenado.auctionms.common.dto.AuctionDto;
import com.danielctrenado.auctionms.common.dto.AuctionRequestDto;
import com.danielctrenado.auctionms.persistence.entity.Auction;
import com.danielctrenado.auctionms.persistence.entity.Category;
import com.danielctrenado.auctionms.persistence.entity.Item;
import com.danielctrenado.auctionms.persistence.repository.AuctionRepository;
import com.danielctrenado.auctionms.persistence.repository.CategoryRepository;
import com.danielctrenado.auctionms.persistence.repository.ItemRepository;
import com.danielctrenado.auctionms.service.AuctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;

    private final ItemRepository itemRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public AuctionServiceImpl(AuctionRepository auctionRepository, ItemRepository itemRepository,
                              CategoryRepository categoryRepository) {
        this.auctionRepository = auctionRepository;
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public AuctionDetailDto createAuction(AuctionRequestDto auctionRequestDto) {
        Category category = this.categoryRepository.findById(auctionRequestDto.getCategoryId()).orElseThrow();
        Item item = this.itemRepository.save(new Item(auctionRequestDto.getProductName(),
                auctionRequestDto.getProductDescription(), category));
        Auction auction = this.auctionRepository.save(new Auction(LocalDateTime.now(), auctionRequestDto.getInitialPrice(),
                auctionRequestDto.getAuctionStart(), auctionRequestDto.getAuctionEnd(), item));

        log.info("[done] create auction");
        return new AuctionDetailDto(auction.getInitialPrice(), auction.getAuctionStart(),
                auction.getAuctionEnd(), item.getName(), item.getDescription(), category.getId(), auction.getId(),
                auction.getCreatedOn());
    }

    @Override
    public List<AuctionDto> getAuctions() {
        List<AuctionDto> auctions = this.auctionRepository.findAll().stream().map(auction
                -> new AuctionDto(auction.getId(), auction.getInitialPrice(), auction.getAuctionStart(),
                auction.getAuctionEnd(), auction.getItem().getName())).collect(Collectors.toList());
        log.info("[done] getAuctions");
        return auctions;
    }

    @Override
    public AuctionDto getAuctionById(Integer id) {
        AuctionDto auctionDto = this.auctionRepository.findById(id).map(auction
                -> new AuctionDto(auction.getId(), auction.getInitialPrice(), auction.getAuctionStart(),
                auction.getAuctionEnd(), auction.getItem().getName())).orElseThrow();
        log.info("[done] getAuctionById {}", id);
        return auctionDto;
    }

}
