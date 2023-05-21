package com.danielctrenado.auctionms.persistence.repository;

import com.danielctrenado.auctionms.persistence.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {
    List<Bid> findByAuctionId(Integer id);
}
