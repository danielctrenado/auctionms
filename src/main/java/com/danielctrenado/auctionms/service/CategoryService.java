package com.danielctrenado.auctionms.service;

import com.danielctrenado.auctionms.common.dto.CategoryDto;
import com.danielctrenado.auctionms.common.dto.CategoryRequestDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryRequestDto categoryRequestDto);

    List<CategoryDto> getAllCategories();
}
