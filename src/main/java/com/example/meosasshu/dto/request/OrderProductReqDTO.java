package com.example.meosasshu.dto.request;

import lombok.Data;

@Data
public class OrderProductReqDTO {
    private Long productId;
    private Long quantity;
}
