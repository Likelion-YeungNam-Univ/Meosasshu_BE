package com.example.meosasshu.repository;

import com.example.meosasshu.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByAccountId(Long accountId, Pageable pageable);
}
