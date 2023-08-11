package com.example.meosasshu.dto.request;

import lombok.Data;

@Data
public class DeliveryReqDTO {
    private String city;
    private String street;
    private String zipcode;

    private String recipientName;
    private String recipientMobileNumber;
    private String deliveryMessage;
}
