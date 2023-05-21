package com.danielctrenado.auctionms.service.impl;

import com.danielctrenado.auctionms.common.dto.CategoryRequestDto;
import com.danielctrenado.auctionms.common.dto.CategoryResponseDto;
import com.danielctrenado.auctionms.persistence.entity.Category;
import com.danielctrenado.auctionms.persistence.repository.CategoryRepository;
import com.danielctrenado.auctionms.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = this.categoryRepository.save(new Category(categoryRequestDto.getName()));
        log.info("[done] category created");
        return new CategoryResponseDto(category.getId(), category.getName());
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<CategoryResponseDto> categoryResponseDtos = this.categoryRepository.findAll().stream().map(category
                -> new CategoryResponseDto(category.getId(), category.getName())).collect(Collectors.toList());
        log.info("[done] getCategories");
        return categoryResponseDtos;
    }
}
