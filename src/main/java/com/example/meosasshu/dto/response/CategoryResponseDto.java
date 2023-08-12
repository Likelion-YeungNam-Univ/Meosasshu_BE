package com.example.meosasshu.dto.response;

import com.example.meosasshu.entity.Category;
import lombok.Data;

@Data
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String description;

    public static CategoryResponseDto createDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }
}