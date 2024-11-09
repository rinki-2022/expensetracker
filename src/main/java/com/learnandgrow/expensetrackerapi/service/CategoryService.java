package com.learnandgrow.expensetrackerapi.service;

import com.learnandgrow.expensetrackerapi.dto.CategoryDTO;
import com.learnandgrow.expensetrackerapi.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCatgeories();
    CategoryDTO saveCategory(CategoryDTO categoryDTO);
}
