package com.example.meosasshu.service;

import com.example.meosasshu.dto.request.SignupReqDTO;
import com.example.meosasshu.dto.response.AccountResDTO;
import com.example.meosasshu.dto.response.AddressResDTO;
import com.example.meosasshu.entity.Account;
import com.example.meosasshu.entity.Address;
import com.example.meosasshu.exception.UserNotFoundException;
import com.example.meosasshu.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    final private AccountRepository accountRepository;

    public AddressResDTO getAddress(Account account) {
        Account foundAccount = accountRepository.findOneWithAuthoritiesByEmail(account.getEmail()).orElseThrow(
                UserNotFoundException::new
        );
        return AddressResDTO.createDto(foundAccount.getAddress());
    }

    public AccountResDTO getProfile(Account account) {
        Account foundAccount = accountRepository.findOneWithAuthoritiesByEmail(account.getEmail()).orElseThrow(
                UserNotFoundException::new
        );
        return AccountResDTO.createDto(foundAccount);
    }

    public AccountResDTO updateProfile(SignupReqDTO reqDTO, Account account) {
        Account foundAccount = accountRepository.findOneWithAuthoritiesByEmail(account.getEmail()).orElseThrow(
                UserNotFoundException::new
        );
        //아래 메소드들은 null이 아닐때만 각각 실행되어야함
        if(reqDTO.getPassword()!=null) foundAccount.setPassword(reqDTO.getPassword());
        if(reqDTO.getName()!=null) foundAccount.setName(reqDTO.getName());

        if(reqDTO.getMobileNumber()!=null) foundAccount.setMobileNumber(reqDTO.getMobileNumber());
        if(reqDTO.getGender()!=null) foundAccount.setGender(reqDTO.getGender());
        if(reqDTO.getBirthDate()!=null) foundAccount.setBirthDate(LocalDate.parse(reqDTO.getBirthDate()));
        if(reqDTO.getCity()!=null&&reqDTO.getStreet()!=null&&reqDTO.getZipcode()!=null) foundAccount.setAddress(new Address(reqDTO.getCity(),reqDTO.getStreet(),reqDTO.getZipcode()));
        return AccountResDTO.createDto(foundAccount);
    }
}
