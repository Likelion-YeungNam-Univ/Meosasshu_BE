package com.example.meosasshu.dto.response;

import com.example.meosasshu.entity.CartProduct;
import com.example.meosasshu.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResDTO {
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
    private double totalPrice;

    public static CartItemResDTO createDTO(CartProduct cartProduct) {
        CartItemResDTO dto = new CartItemResDTO();
        Product product = cartProduct.getProduct();
        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity((int) cartProduct.getQuantity());
        dto.setTotalPrice(product.getPrice() * dto.getQuantity());
        return dto;
    }
}