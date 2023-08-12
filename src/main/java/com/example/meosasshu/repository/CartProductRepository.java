package com.example.meosasshu.repository;

import com.example.meosasshu.entity.Cart;
import com.example.meosasshu.entity.CartProduct;
import com.example.meosasshu.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    Optional<CartProduct> findByCartAndProduct(Cart cart, Product product);
    List<CartProduct> findByCart(Cart cart);
}
