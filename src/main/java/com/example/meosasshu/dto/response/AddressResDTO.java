package com.example.meosasshu.dto.response;

import com.example.meosasshu.entity.Address;
import lombok.Data;

@Data
public class AddressResDTO {
    private String city;
    private String street;
    private String zipcode;

    public static AddressResDTO createDto(Address address) {
        AddressResDTO dto = new AddressResDTO();
        dto.setCity(address.getCity());
        dto.setStreet(address.getStreet());
        dto.setZipcode(address.getZipcode());
        return dto;
    }
}
