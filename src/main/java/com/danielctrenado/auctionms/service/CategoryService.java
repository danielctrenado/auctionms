package com.danielctrenado.auctionms.service;

import com.danielctrenado.auctionms.common.dto.CategoryRequestDto;
import com.danielctrenado.auctionms.common.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

    List<CategoryResponseDto> getAllCategories();
}
