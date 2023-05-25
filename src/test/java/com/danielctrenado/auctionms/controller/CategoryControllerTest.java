package com.danielctrenado.auctionms.controller;

import com.danielctrenado.auctionms.common.dto.CategoryDto;
import com.danielctrenado.auctionms.common.dto.CategoryRequestDto;
import com.danielctrenado.auctionms.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class CategoryControllerTest {

    private static final String CATEGORIES_URI = "/api/v1/categories";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;


    @Test
    void getCategories_ok() throws Exception {
        Mockito.when(categoryService.getAllCategories()).thenReturn(List.of());

        this.mockMvc.perform(MockMvcRequestBuilders.get(CATEGORIES_URI)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(0)));
    }

    @Test
    void getCategories_serverError() throws Exception {
        Mockito.when(categoryService.getAllCategories()).thenThrow(new RuntimeException("Error"));

        this.mockMvc.perform(MockMvcRequestBuilders.get(CATEGORIES_URI)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void createCategory_ok() throws Exception {
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto("Pharmacy");
        CategoryDto categoryDto = new CategoryDto(1, "Pharmacy");
        Mockito.when(this.categoryService.createCategory(categoryRequestDto)).thenReturn(categoryDto);


        this.mockMvc.perform(MockMvcRequestBuilders.post(CATEGORIES_URI)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categoryRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(categoryRequestDto.getName()));
    }

    @Test
    void createCategory_serverError() throws Exception {
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto("CategoryForError");
        Mockito.when(this.categoryService.createCategory(categoryRequestDto))
                .thenThrow(new RuntimeException("Error"));

        this.mockMvc.perform(MockMvcRequestBuilders.post(CATEGORIES_URI)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categoryRequestDto)))
                .andExpect(status().is5xxServerError());
    }
}
