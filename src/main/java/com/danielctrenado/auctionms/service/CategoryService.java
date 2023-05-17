package com.danielctrenado.auctionms.service;

import com.danielctrenado.auctionms.common.dto.CategoryResponseDto;

import java.util.Set;

public interface CategoryService {
    CategoryResponseDto createCategory(String name);

    Set<CategoryResponseDto> getAllCategories();
}
