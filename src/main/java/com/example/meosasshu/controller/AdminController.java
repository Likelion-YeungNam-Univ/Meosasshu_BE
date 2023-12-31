package com.example.meosasshu.controller;


import com.example.meosasshu.dto.request.SignupReqDTO;
import com.example.meosasshu.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    @GetMapping("") //테스트용
    @Secured("ROLE_ADMIN")
    ResponseEntity<String> adminOnly(){
        return ResponseEntity.ok("success");
    }

    @GetMapping("/getAllUsers")
    @Secured("ROLE_ADMIN")
    ResponseEntity<List<SignupReqDTO>> getAllUsers(){
        List<SignupReqDTO> accountDtos = adminService.getAllUsers();
        return ResponseEntity.ok(accountDtos);
    }

    @GetMapping("/account/{accountId}")
    @Secured("ROLE_ADMIN")
    ResponseEntity<SignupReqDTO> getAccount(@PathVariable Long accountId){
        SignupReqDTO accountDto = adminService.getAccountDtoByAccountId(accountId);
        return ResponseEntity.ok(accountDto);
    }

    @PatchMapping("/account/{accountId}")
    @Secured("ROLE_ADMIN")
    ResponseEntity<Void> assignAdminRole(@PathVariable Long accountId){
        adminService.assignAdminRole(accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
