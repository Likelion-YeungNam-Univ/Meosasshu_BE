package com.example.meosasshu.entity;

import com.example.meosasshu.dto.request.DeliveryReqDTO;
import com.example.meosasshu.dto.request.SignupReqDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Address extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    private String city;
    private String street;
    private String zipcode;

    public Address(String city, String street, String zipcode) {
        super();
    }

    public Address(SignupReqDTO signupReqDto) {
        this.city = signupReqDto.getCity();
        this.street = signupReqDto.getStreet();
        this.zipcode = signupReqDto.getZipcode();
    }

    public static Address createAddress(DeliveryReqDTO delivery) {
        Address newAddress = new Address();
        newAddress.city = delivery.getCity();
        newAddress.street = delivery.getStreet();
        newAddress.zipcode = delivery.getZipcode();
        return newAddress;
    }
}
