package com.example.meosasshu.controller;

import com.example.meosasshu.dto.request.CartItemReqDTO;
import com.example.meosasshu.dto.response.CartItemResDTO;
import com.example.meosasshu.dto.response.OrderFormResDTO;
import com.example.meosasshu.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestHeader("accessToken") String accessToken,
                                            @RequestHeader("refreshToken") String refreshToken,
                                            @RequestBody CartItemReqDTO cartItemRequest) {
        cartService.addToCart(accessToken, refreshToken, cartItemRequest);
        return ResponseEntity.created(null).body("장바구니에 추가되었습니다.");
    }

    @GetMapping
    public ResponseEntity<List<CartItemResDTO>> getCartItems(@RequestHeader("accessToken") String accessToken,
                                                             @RequestHeader("refreshToken") String refreshToken) {
        List<CartItemResDTO> cartItems = cartService.getCartItems(accessToken, refreshToken);
        return ResponseEntity.ok(cartItems);
    }

    @PutMapping("/items/{productId}")
    public ResponseEntity<CartItemResDTO> updateCartItemQuantity(@RequestHeader("accessToken") String accessToken,
                                                                 @RequestHeader("refreshToken") String refreshToken,
                                                                 @PathVariable Long productId,
                                                                 @RequestBody Map<String, Integer> requestBody) {
        int quantity = requestBody.get("quantity");
        CartItemResDTO updatedCartItem = cartService.updateCartItemQuantity(accessToken, refreshToken, productId, quantity);
        return ResponseEntity.ok(updatedCartItem);
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeCartItem(@RequestHeader("accessToken") String accessToken,
                                               @RequestHeader("refreshToken") String refreshToken,
                                               @PathVariable Long productId) {
        cartService.removeCartItem(accessToken, refreshToken, productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/order-form")
    public ResponseEntity<OrderFormResDTO> getOrderForm(@RequestHeader("accessToken") String accessToken,
                                                        @RequestHeader("refreshToken") String refreshToken) {
        OrderFormResDTO orderForm = cartService.getOrderForm(accessToken, refreshToken);
        return ResponseEntity.ok(orderForm);
    }
}