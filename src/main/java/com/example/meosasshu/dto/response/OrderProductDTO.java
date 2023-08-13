package com.example.meosasshu.dto.response;

import com.example.meosasshu.entity.OrderProduct;
import com.example.meosasshu.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    public static OrderProductDTO createDto(Product product, Long quantity) {
        OrderProductDTO dto = new OrderProductDTO();
        dto.setProductName(product.getName());
        dto.setBrand(product.getBrand());
        dto.setImageUrl(product.getThumbnail());
        dto.setQuantity(quantity);
        dto.setTotalPrice(quantity*product.getPrice());
        return dto;
    }

    public static List<OrderProductDTO> createDtoList(List<OrderProduct> orderProducts) {
        List<OrderProductDTO> dtos = new ArrayList<>();
        for (OrderProduct orderProduct : orderProducts) {
            dtos.add(createDto(orderProduct.getProduct(), orderProduct.getQuantity()));
        }
        return dtos;
    }
}
