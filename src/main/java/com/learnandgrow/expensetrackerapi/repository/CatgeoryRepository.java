package com.learnandgrow.expensetrackerapi.repository;

import com.learnandgrow.expensetrackerapi.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatgeoryRepository extends JpaRepository<CategoryEntity, Long> {
}
