package com.example.meosasshu.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderReqDTO {
    private List<OrderProductReqDTO> orderProducts;
    private DeliveryReqDTO delivery;
}
