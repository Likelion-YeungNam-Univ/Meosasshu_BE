package com.example.meosasshu.controller;

import com.example.meosasshu.dto.response.CategoryResponseDto;
import com.example.meosasshu.dto.response.ProductPagingDTO;
import com.example.meosasshu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryResponseDto>> getCategory() {
        List<CategoryResponseDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/category/{categoryId}/products")
    public ResponseEntity<List<ProductPagingDTO>> getCategoryProducts(@PathVariable Long categoryId) {
        List<ProductPagingDTO> products = categoryService.getCategoryProducts(categoryId);
        return ResponseEntity.ok(products);
    }
}