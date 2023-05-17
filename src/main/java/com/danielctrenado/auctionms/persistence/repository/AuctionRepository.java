package com.danielctrenado.auctionms.persistence.repository;

import com.danielctrenado.auctionms.persistence.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Integer> {

}
