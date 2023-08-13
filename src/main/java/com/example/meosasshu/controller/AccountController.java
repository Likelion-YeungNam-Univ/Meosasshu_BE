package com.example.meosasshu.controller;


import com.example.meosasshu.common.security.user.CurrentUser;
import com.example.meosasshu.common.security.user.UserDetailsImpl;
import com.example.meosasshu.dto.request.SignupReqDTO;
import com.example.meosasshu.dto.response.AccountResDTO;
import com.example.meosasshu.dto.response.AddressResDTO;
import com.example.meosasshu.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    final private AccountService accountService;
    @Secured("ROLE_USER")
    @GetMapping("/address")
    public ResponseEntity<AddressResDTO> getAddress(@AuthenticationPrincipal UserDetailsImpl userDetails){
        AddressResDTO dto = accountService.getAddress(userDetails.getAccount());
        return ResponseEntity.ok(dto);
    }

    @Secured("ROLE_USER")
    @GetMapping("/profile")
    public ResponseEntity<AccountResDTO> getProfile(@CurrentUser UserDetailsImpl userDetails){
        AccountResDTO dto = accountService.getProfile(userDetails.getAccount());
        return ResponseEntity.ok(dto);
    }

    @Secured("ROLE_USER")
    @PutMapping("/profile")
    public ResponseEntity<AccountResDTO> updateProfile(@RequestBody SignupReqDTO reqDTO, @CurrentUser UserDetailsImpl userDetails){
        AccountResDTO dto = accountService.updateProfile(reqDTO,userDetails.getAccount());
        return ResponseEntity.ok(dto);
    }


}
