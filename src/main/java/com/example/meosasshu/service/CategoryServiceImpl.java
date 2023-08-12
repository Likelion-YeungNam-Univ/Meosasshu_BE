package com.example.meosasshu.service;

import com.example.meosasshu.dto.response.CategoryResponseDto;
import com.example.meosasshu.dto.response.ProductPagingDTO;
import com.example.meosasshu.entity.Category;
import com.example.meosasshu.entity.Product;
import com.example.meosasshu.repository.CategoryRepository;
import com.example.meosasshu.repository.ProductRepository;
import com.example.meosasshu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> {
                    CategoryResponseDto dto = new CategoryResponseDto();
                    dto.setId(category.getId());
                    dto.setName(category.getName());
                    dto.setDescription(category.getDescription());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductPagingDTO> getCategoryProducts(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return products.stream()
                .map(ProductPagingDTO::createDto)
                .collect(Collectors.toList());
    }
}
