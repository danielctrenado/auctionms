package com.danielctrenado.auctionms.service.impl;

import com.danielctrenado.auctionms.common.dto.CategoryDto;
import com.danielctrenado.auctionms.common.dto.CategoryRequestDto;
import com.danielctrenado.auctionms.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        CategoryDto categoryDto = this.categoryService.createCategory(new CategoryRequestDto(name));
        assertEquals(name, categoryDto.getName());
        assertNotNull(categoryDto.getId());
    }

    @Test
    void getCategories_ok() {
        this.categoryService.createCategory(new CategoryRequestDto("category1"));
        this.categoryService.createCategory(new CategoryRequestDto("category2"));

        List<CategoryDto> categories = this.categoryService.getAllCategories();
        assertEquals(3, categories.size());
    }
}
