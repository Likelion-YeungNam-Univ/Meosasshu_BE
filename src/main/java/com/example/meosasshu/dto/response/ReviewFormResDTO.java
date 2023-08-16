package com.example.meosasshu.dto.response;

import com.example.meosasshu.entity.OrderProduct;
import lombok.Data;

@Data
public class ReviewFormResDTO {
    private String brand;
    private String productName;
    private Long quantity;
    private String orderDate;

    public static ReviewFormResDTO createDto(OrderProduct orderProduct) {
        ReviewFormResDTO dto = new ReviewFormResDTO();
        dto.setBrand(orderProduct.getProduct().getBrand());
        dto.setProductName(orderProduct.getProduct().getName());
        dto.setQuantity(orderProduct.getQuantity());
        dto.setOrderDate(orderProduct.getOrder().getCreatedAt().toString());
        return dto;
    }
}
