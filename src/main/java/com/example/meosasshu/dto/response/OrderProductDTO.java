package com.example.meosasshu.dto.response;

import com.example.meosasshu.entity.Product;
import lombok.Getter;
import lombok.Setter;

/**
 * 상품주문 페이지에 들어갈 간단한 상품정보+ 주문개수, 총가격
 * */
@Getter @Setter
public class OrderProductDTO {
    private String productName;
    private String brand;
    private Long quantity;
    private double totalPrice;
    private String imageUrl;

    public static OrderProductDTO createDto(Product product) {
        OrderProductDTO dto = new OrderProductDTO();
        dto.setProductName(product.getName());
        dto.setBrand(product.getBrand());
        dto.setImageUrl(product.getThumbnail());
        return dto;
    }
}
