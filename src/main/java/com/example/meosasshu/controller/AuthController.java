package com.example.meosasshu.controller;

import com.example.meosasshu.dto.request.SignupReqDto;
import com.example.meosasshu.dto.request.LoginReqDto;
import com.example.meosasshu.entity.Account;
import com.example.meosasshu.security.jwt.TokenDto;
import com.example.meosasshu.security.user.CurrentUser;
import com.example.meosasshu.security.user.UserDetailsImpl;
import com.example.meosasshu.service.AccountService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody @Valid SignupReqDto signupReqDto) {
        Long accountId= accountService.signup(signupReqDto);
        return ResponseEntity.ok(accountId);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginReqDto loginReqDto, HttpServletResponse response) {
        accountService.login(loginReqDto, response);
        return new ResponseEntity<>("로그인 성공",HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(@CurrentUser UserDetailsImpl userDetails, @RequestBody TokenDto tokenDto){
        return ResponseEntity.ok(accountService.logout(tokenDto.getAccessToken(),userDetails.getAccount()));
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public ResponseEntity<String> adminOnlyMethod() {
        // ROLE_ADMIN 권한이 있는 사용자만 실행 가능한 로직
        return new ResponseEntity<>("success!!",HttpStatus.OK);
    }

    @GetMapping("/current-user")
    public ResponseEntity<Account> getCurrentUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.ok(userDetails.getAccount()); // 로그인되지 않은 경우에 대한 처리
    }

    @GetMapping("/getAuth")
    public List<String> getAuth(@CurrentUser UserDetailsImpl userDetails) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            return authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
        }

        return List.of("No authorities found");
    }
}