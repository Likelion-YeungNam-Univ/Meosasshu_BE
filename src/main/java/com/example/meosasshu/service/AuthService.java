package com.example.meosasshu.service;


import com.example.meosasshu.common.redis.RedisUtil;
import com.example.meosasshu.common.security.jwt.JwtUtil;
import com.example.meosasshu.common.security.jwt.RefreshToken;
import com.example.meosasshu.common.security.jwt.RefreshTokenRepository;
import com.example.meosasshu.common.security.jwt.TokenDto;
import com.example.meosasshu.dto.request.LoginReqDTO;
import com.example.meosasshu.dto.request.SignupReqDTO;
import com.example.meosasshu.entity.Account;
import com.example.meosasshu.entity.Address;
import com.example.meosasshu.entity.Authority;
import com.example.meosasshu.exception.DuplicateNicknameException;
import com.example.meosasshu.exception.UserNotFoundException;
import com.example.meosasshu.repository.AccountRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisUtil redisUtil;
    @Transactional
    public Long signup(SignupReqDTO signupReqDto) {
        if(accountRepository.findOneWithAuthoritiesByEmail(signupReqDto.getEmail()).isPresent()){
            throw new DuplicateNicknameException();
        }

        signupReqDto.setEncodePwd(passwordEncoder.encode(signupReqDto.getPassword()));

        Address address = new Address(signupReqDto);
        Account account = new Account(signupReqDto, address);

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();
        account.setAuthorities(Collections.singleton(authority));
        accountRepository.save(account);
        return account.getId();
    }

    @Transactional
    public TokenDto login(LoginReqDTO loginReqDto, HttpServletResponse response) {

        Account account = accountRepository.findOneWithAuthoritiesByEmail(loginReqDto.getEmail()).orElseThrow(
                UserNotFoundException::new
        );

        if(!passwordEncoder.matches(loginReqDto.getPassword(), account.getPassword())) {
            throw new RuntimeException("Not matches Password");
        }

        TokenDto tokenDto = jwtUtil.createAllToken(loginReqDto.getEmail(),account.getAuthorities());

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByAccountEmail(loginReqDto.getEmail());

        if(refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
        }else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginReqDto.getEmail());
            refreshTokenRepository.save(newToken);
        }

        setHeader(response, tokenDto);
        return tokenDto;
    }

    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }

    public Set<Authority> getAuthorities(String loginId) {
        Account account = accountRepository.findOneWithAuthoritiesByEmail(loginId).orElseThrow(
        );
        return account.getAuthorities();
    }

    public String logout(String accessToken, Account account) {
        refreshTokenRepository.deleteRefreshTokenByAccountEmail(account.getEmail());
        redisUtil.setBlackList(accessToken,"accessToken",5);

        return "로그아웃 완료";
    }
}
