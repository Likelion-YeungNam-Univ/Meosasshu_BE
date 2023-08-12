package com.example.meosasshu.repository;

import com.example.meosasshu.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByOrderBySalesCountDesc(Pageable pageable);

    List<Product> findByCategoryId(Long categoryId);
}
