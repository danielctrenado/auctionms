package com.danielctrenado.auctionms.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bids")
@NoArgsConstructor
@Data
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_on", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdOn;

    private BigDecimal amount;

    @ManyToOne
    private Auction auction;

    public Bid(LocalDateTime createdOn, BigDecimal amount, Auction auction) {
        this.createdOn = createdOn;
        this.amount = amount;
        this.auction = auction;
    }
}
