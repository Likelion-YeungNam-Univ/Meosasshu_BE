package com.example.meosasshu.controller;

import com.example.meosasshu.common.security.user.UserDetailsImpl;
import com.example.meosasshu.dto.request.CartItemReqDTO;
import com.example.meosasshu.dto.response.CartItemResDTO;
import com.example.meosasshu.dto.response.OrderFormResDTO;
import com.example.meosasshu.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                            @RequestBody CartItemReqDTO cartItemRequest) {
        cartService.addToCart(userDetails.getAccount(), cartItemRequest);
        return ResponseEntity.created(null).body("장바구니에 추가되었습니다.");
    }

//    @GetMapping
//    public ResponseEntity<List<CartItemResDTO>> getCartItems(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        List<CartItemResDTO> cartItems = cartService.getCartItems(userDetails.getAccount());
//        return ResponseEntity.ok(cartItems);
//    }

    @PutMapping("/items/{productId}")
    public ResponseEntity<CartItemResDTO> updateCartItemQuantity(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                 @PathVariable Long productId,
                                                                 @RequestBody Map<String, Integer> requestBody) {
        int quantity = requestBody.get("quantity");
        CartItemResDTO updatedCartItem = cartService.updateCartItemQuantity(userDetails.getAccount(), productId, quantity);
        return ResponseEntity.ok(updatedCartItem);
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeCartItem(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                               @PathVariable Long productId) {
        cartService.removeCartItem(userDetails.getAccount(), productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<OrderFormResDTO> getOrderForm(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        OrderFormResDTO orderForm = cartService.getOrderForm(userDetails.getAccount());
        return ResponseEntity.ok(orderForm);
    }
}