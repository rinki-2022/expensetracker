package com.learnandgrow.expensetrackerapi.controller;

import com.learnandgrow.expensetrackerapi.dto.CategoryDTO;
import com.learnandgrow.expensetrackerapi.io.CategoryRequest;
import com.learnandgrow.expensetrackerapi.io.CategoryResponse;
import com.learnandgrow.expensetrackerapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryDTO categoryDTO = mapTOCatgeoryDTO(categoryRequest);
        categoryDTO = categoryService.saveCategory(categoryDTO);
        return mapToResponse(categoryDTO);
    }

    private CategoryResponse mapToResponse(CategoryDTO categoryDTO) {
        return CategoryResponse.builder()
                .categoryId(categoryDTO.getCategoryId())
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .categoryIcon(categoryDTO.getCategoryIcon())
                .createdAt(categoryDTO.getCreatedAt())
                .updatedAt(categoryDTO.getUpdatedAt())
                .build();
    }

    private CategoryDTO mapTOCatgeoryDTO(CategoryRequest categoryRequest) {
        return CategoryDTO.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .categoryIcon(categoryRequest.getIcon())
                .build();
    }
}
