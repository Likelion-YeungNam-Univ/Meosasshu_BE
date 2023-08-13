package com.example.meosasshu.dto.response;

import com.example.meosasshu.entity.Delivery;
import com.example.meosasshu.enums.DeliveryStatus;
import lombok.Data;

@Data
public class DeliveryResDTO {
    private String city;
    private String street;
    private String zipcode;

    private String recipientName;
    private String recipientMobileNumber;
    private String deliveryMessage;

    private DeliveryStatus deliveryStatus;

    public static DeliveryResDTO createDto(Delivery delivery) {
        DeliveryResDTO dto = new DeliveryResDTO();
        dto.setCity(delivery.getAddress().getCity());
        dto.setStreet(delivery.getAddress().getStreet());
        dto.setZipcode(delivery.getAddress().getZipcode());
        dto.setRecipientName(delivery.getRecipientName());
        dto.setRecipientMobileNumber(delivery.getRecipientMobileNumber());
        dto.setDeliveryMessage(delivery.getDeliveryMessage());
        dto.setDeliveryStatus(delivery.getStatus());
        return dto;

    }
}
