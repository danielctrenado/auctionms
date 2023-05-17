package com.danielctrenado.auctionms.service.impl;

import com.danielctrenado.auctionms.common.dto.CategoryResponseDto;
import com.danielctrenado.auctionms.persistence.entity.Category;
import com.danielctrenado.auctionms.persistence.repository.CategoryRepository;
import com.danielctrenado.auctionms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDto createCategory(String name) {
        Category category = this.categoryRepository.save(new Category(name));
        return new CategoryResponseDto(category.getId(), category.getName());
    }

    @Override
    public Set<CategoryResponseDto> getAllCategories() {
        return this.categoryRepository.findAll().stream().map(category -> new CategoryResponseDto(category.getId(),
                category.getName())).collect(Collectors.toSet());
    }
}
