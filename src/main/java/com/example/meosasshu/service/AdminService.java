package com.example.meosasshu.service;


import com.example.meosasshu.dto.request.SignupReqDto;
import com.example.meosasshu.entity.Account;
import com.example.meosasshu.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class AdminService {
    private final AccountRepository accountRepository;

    public List<SignupReqDto> getAllUsers() {
        List<Account> accounts = accountRepository.findAll();
        List<SignupReqDto> accountDtos = new ArrayList<>(accounts.size());
        for(Account account: accounts){
            accountDtos.add(new SignupReqDto(account));
        }
        return accountDtos;
    }

    public SignupReqDto getAccountDtoByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                ()->new RuntimeException("Invalid accountId: "+accountId)
        );
        return new SignupReqDto(account);
    }

    public void assignAdminRole(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                ()->new RuntimeException("Invalid accountId: "+accountId)
        );
    }
}
