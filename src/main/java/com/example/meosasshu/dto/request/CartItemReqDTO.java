package com.example.meosasshu.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemReqDTO {
    private Long productId;
    private int quantity;
}