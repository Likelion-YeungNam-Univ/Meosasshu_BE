package com.example.meosasshu.service;


import com.example.meosasshu.dto.request.SignupReqDTO;
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

    public List<SignupReqDTO> getAllUsers() {
        List<Account> accounts = accountRepository.findAll();
        List<SignupReqDTO> accountDtos = new ArrayList<>(accounts.size());
        for(Account account: accounts){
            accountDtos.add(new SignupReqDTO(account));
        }
        return accountDtos;
    }

    public SignupReqDTO getAccountDtoByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                ()->new RuntimeException("Invalid accountId: "+accountId)
        );
        return new SignupReqDTO(account);
    }

    public void assignAdminRole(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                ()->new RuntimeException("Invalid accountId: "+accountId)
        );
    }
}
