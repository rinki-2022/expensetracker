package com.learnandgrow.expensetrackerapi.service;

import com.learnandgrow.expensetrackerapi.dto.CategoryDTO;
import com.learnandgrow.expensetrackerapi.dto.UserDTO;
import com.learnandgrow.expensetrackerapi.entity.CategoryEntity;
import com.learnandgrow.expensetrackerapi.entity.User;
import com.learnandgrow.expensetrackerapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final UserService userService;

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getAllCatgeories() {
        List<CategoryEntity> list = categoryRepository.findByUserId(userService.getLoggedInUser().getId());
        return list.stream().map(categoryEntity -> mapToDTO(categoryEntity)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        CategoryEntity newCategory = mapToEntity(categoryDTO);
        newCategory = categoryRepository.save(newCategory);
        return mapToDTO(newCategory);
    }

    private CategoryEntity mapToEntity(CategoryDTO categoryDTO) {
        return CategoryEntity.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .categoryIcon(categoryDTO.getCategoryIcon())
                .categoryId(UUID.randomUUID().toString())
                .user(userService.getLoggedInUser())
                .build();

    }

    private CategoryDTO mapToDTO(CategoryEntity categoryEntity) {
        return CategoryDTO.builder()
                .categoryId(categoryEntity.getCategoryId())
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .categoryIcon(categoryEntity.getCategoryIcon())
                .createdAt(categoryEntity.getCreatedAt())
                .updatedAt(categoryEntity.getUpdatedAt())
                .userDTO(mapTpUserDTO(categoryEntity.getUser()))
                .build();
    }

    private UserDTO mapTpUserDTO(User user) {

        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
