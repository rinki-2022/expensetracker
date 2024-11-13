package com.learnandgrow.expensetrackerapi.repository;

import com.learnandgrow.expensetrackerapi.dto.CategoryDTO;
import com.learnandgrow.expensetrackerapi.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findByUserId(Long userId);
    CategoryEntity save(CategoryEntity categoryEntity);
    Optional<CategoryEntity> findByUserIdAndCategoryId (Long userId, String categoryId);
    boolean existsByNameAndUserId(String name, Long userId);
}
