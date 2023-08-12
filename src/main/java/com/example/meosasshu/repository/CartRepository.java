package com.example.meosasshu.repository;

import com.example.meosasshu.entity.Cart;
import com.example.meosasshu.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByAccount(Account account);
}