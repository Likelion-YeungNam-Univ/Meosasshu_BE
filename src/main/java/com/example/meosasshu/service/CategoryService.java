package com.example.meosasshu.service;


import com.example.meosasshu.dto.response.CategoryResponseDto;
import com.example.meosasshu.dto.response.ProductPagingDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();
    List<ProductPagingDTO> getCategoryProducts(Long categoryId);
}
