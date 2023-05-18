package com.danielctrenado.auctionms.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto extends CategoryRequestDto {
    private Integer id;

    public CategoryResponseDto(Integer id, String name) {
        super(name);
        this.id = id;
    }
}
