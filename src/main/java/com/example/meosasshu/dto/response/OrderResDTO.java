package com.example.meosasshu.dto.response;

import com.example.meosasshu.entity.Order;
import com.example.meosasshu.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResDTO {
    private Long orderId;
    private List<OrderProductDTO> orderProducts;
    private DeliveryResDTO deliveryInfo;
    private double totalPrice;
    private OrderStatus status;
    private LocalDateTime orderDate;

    public static OrderResDTO createDto(Order order) {
        OrderResDTO dto = new OrderResDTO();
        dto.setOrderId(order.getId());
        dto.setOrderProducts(OrderProductDTO.createDtoList(order.getOrderProducts()));
        dto.setDeliveryInfo(DeliveryResDTO.createDto(order.getDelivery()));
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus());
        dto.setOrderDate(order.getCreatedAt());
        return dto;

    }
}

