package com.example.meosasshu.service;

import com.example.meosasshu.dto.request.CartItemReqDTO;
import com.example.meosasshu.dto.response.CartItemResDTO;
import com.example.meosasshu.dto.response.OrderFormResDTO;

import java.util.List;

public interface CartService {
    void addToCart(String accessToken, String refreshToken, CartItemReqDTO cartItemRequest);
    List<CartItemResDTO> getCartItems(String accessToken, String refreshToken);
    CartItemResDTO updateCartItemQuantity(String accessToken, String refreshToken, Long productId, int quantity);
    void removeCartItem(String accessToken, String refreshToken, Long productId);
    OrderFormResDTO getOrderForm(String accessToken, String refreshToken);
}