package com.danielctrenado.auctionms.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @OneToOne
    private Category category;

    public Item(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }
}
