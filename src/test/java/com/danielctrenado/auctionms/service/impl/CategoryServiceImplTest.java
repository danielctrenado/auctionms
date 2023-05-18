package com.danielctrenado.auctionms.service.impl;

import com.danielctrenado.auctionms.common.dto.CategoryResponseDto;
import com.danielctrenado.auctionms.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CategoryServiceImplTest {

    private final CategoryService categoryService;

    @Autowired
    public CategoryServiceImplTest(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Test
    void createCategory_ok() {
        String name = "category1";
        CategoryResponseDto categoryResponseDto = this.categoryService.createCategory(name);
        assertEquals(name, categoryResponseDto.getName());
        assertNotNull(categoryResponseDto.getId());
    }

    @Test
    void getCategories_ok() {
        this.categoryService.createCategory("category1");
        this.categoryService.createCategory("category2");

        Set<CategoryResponseDto> categories = this.categoryService.getAllCategories();
        assertEquals(3, categories.size());
    }
}
