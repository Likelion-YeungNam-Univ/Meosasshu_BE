package com.example.meosasshu.service;

import com.example.meosasshu.dto.request.CartItemReqDTO;
import com.example.meosasshu.dto.response.CartItemResDTO;
import com.example.meosasshu.dto.response.OrderFormResDTO;
import com.example.meosasshu.entity.Account;

import java.util.List;

public interface CartService {
    void addToCart(Account account, CartItemReqDTO cartItemRequest);
    List<CartItemResDTO> getCartItems(Account account);
    CartItemResDTO updateCartItemQuantity(Account account, Long productId, int quantity);
    void removeCartItem(Account account, Long productId);
    OrderFormResDTO getOrderForm(Account account);
}