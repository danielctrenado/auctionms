package com.danielctrenado.auctionms.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto extends CategoryRequestDto {
    private Integer id;

    public CategoryDto(Integer id, String name) {
        super(name);
        this.id = id;
    }
}
