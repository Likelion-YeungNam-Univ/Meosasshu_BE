package com.example.meosasshu.dto.response;

import com.example.meosasshu.entity.Account;
import lombok.Data;

@Data
public class AccountResDTO {
private String email;
    private String name;
    private AddressResDTO address;
    private String mobileNumber;
    private String gender;
    private String birthDate;


    public static AccountResDTO createDto(Account foundAccount) {
        AccountResDTO accountResDTO = new AccountResDTO();
        accountResDTO.setEmail(foundAccount.getEmail());
        accountResDTO.setName(foundAccount.getName());
        accountResDTO.setAddress(AddressResDTO.createDto(foundAccount.getAddress()));
        accountResDTO.setMobileNumber(foundAccount.getMobileNumber());
        accountResDTO.setGender(foundAccount.getGender());
        accountResDTO.setBirthDate(String.valueOf(foundAccount.getBirthDate()));
        return accountResDTO;
    }
}
