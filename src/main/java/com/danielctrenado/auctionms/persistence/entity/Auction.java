package com.danielctrenado.auctionms.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_on", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdOn;

    @Column(name = "initial_price")
    private BigDecimal initialPrice;

    @Column(name = "auction_start", columnDefinition = "TIMESTAMP")
    private LocalDateTime auctionStart;

    @Column(name = "auction_end", columnDefinition = "TIMESTAMP")
    private LocalDateTime auctionEnd;

    @OneToOne
    private Item item;

    public Auction(LocalDateTime createdOn, BigDecimal initialPrice, LocalDateTime auctionStart,
                   LocalDateTime auctionEnd, Item item) {
        this.createdOn = createdOn;
        this.initialPrice = initialPrice;
        this.auctionStart = auctionStart;
        this.auctionEnd = auctionEnd;
        this.item = item;
    }
}
