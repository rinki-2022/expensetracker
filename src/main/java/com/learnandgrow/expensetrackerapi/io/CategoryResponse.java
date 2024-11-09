package com.learnandgrow.expensetrackerapi.io;

import com.learnandgrow.expensetrackerapi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {


    private String categoryId;
    private String name;
    private String description;
    private String categoryIcon;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
