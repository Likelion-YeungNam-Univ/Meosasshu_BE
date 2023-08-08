package com.example.meosasshu.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 * 주문 페이지 접속시 보여줘야할 정보들. 주문할 상품들 간단한 정보와 총 가격
 * */

@Setter @Getter
public class OrderFormResDTO {
    private List<OrderProductDTO> orderProducts;
    private double totalPrice;

    public static OrderFormResDTO createDto(List<OrderProductDTO> orderProductDTOs) {
        OrderFormResDTO dto = new OrderFormResDTO();

        double totalPrice = 0;
        dto.setOrderProducts(orderProductDTOs);
        for(OrderProductDTO orderProduct: orderProductDTOs){
            totalPrice+= orderProduct.getTotalPrice();
        }

        dto.setTotalPrice(totalPrice);

        return dto;
    }
}
