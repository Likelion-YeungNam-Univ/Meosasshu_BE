package com.example.meosasshu.entity;

import com.example.meosasshu.dto.request.DeliveryReqDTO;
import com.example.meosasshu.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
public class Delivery extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
    private Address address;

    private String deliveryMessage;
    private String recipientName;
    private String recipientMobileNumber;

    private DeliveryStatus status;

    public static Delivery createDelivery(DeliveryReqDTO delivery) {
        Delivery newDelivery = new Delivery();
        newDelivery.address = Address.createAddress(delivery);
        newDelivery.deliveryMessage = delivery.getDeliveryMessage();
        newDelivery.recipientName = delivery.getRecipientName();
        newDelivery.recipientMobileNumber = delivery.getRecipientMobileNumber();
        newDelivery.status = DeliveryStatus.READY;
        return newDelivery;

    }
}
