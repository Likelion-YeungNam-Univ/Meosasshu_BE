package com.example.meosasshu.service;

import com.example.meosasshu.dto.response.AddressResDTO;
import com.example.meosasshu.entity.Account;
import com.example.meosasshu.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    final private AccountRepository accountRepository;

    public AddressResDTO getAddress(Account account) {
        Account foundAccount = accountRepository.findOneWithAuthoritiesByEmail(account.getEmail()).orElseThrow(
                () -> new RuntimeException("Not Found Account")
        );
        return AddressResDTO.createDto(foundAccount.getAddress());
    }
}
