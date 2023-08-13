package com.example.meosasshu.common.security.jwt;


import com.example.meosasshu.common.security.user.UserDetailsServiceImpl;
import com.example.meosasshu.entity.Authority;
import com.example.meosasshu.common.redis.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final UserDetailsServiceImpl userDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisUtil redisUtil;

    private static final long ACCESS_TIME = 30 * 60 * 1000L;
    private static final long REFRESH_TIME =  7 * 24 * 60 * 60 * 1000L;

    public static final String ACCESS_TOKEN = "accessToken";
    public static final String REFRESH_TOKEN = "refreshToken";

    private static final String AUTHORITIES_KEY = "authorities";


    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String getHeaderToken(HttpServletRequest request, String type) {
        return type.equals("Access") ? request.getHeader(ACCESS_TOKEN) :request.getHeader(REFRESH_TOKEN);
    }

    public TokenDto createAllToken(String email, Set<Authority> authorities) {
        return new TokenDto(createAccessToken(email, authorities), createRefreshToken(email,authorities));
    }

    public String createAccessToken(String email, Set<Authority> authorities) {

        Date date = new Date();

        long time= ACCESS_TIME;


        String str_authorities = authorities.stream()
                .map(Authority::getAuthorityName)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(date.getTime() + time))
                .setIssuedAt(date)
                .claim(AUTHORITIES_KEY,str_authorities)
                .signWith(key, signatureAlgorithm)
                .compact();

    }

    public String createRefreshToken(String email, Set<Authority> authorities) {

        Date date = new Date();

        long time = REFRESH_TIME;
        String str_authorities = authorities.stream()
                .map(Authority::getAuthorityName)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(date.getTime() + time))
                .setIssuedAt(date)
                .claim(AUTHORITIES_KEY,str_authorities)
                .signWith(key, signatureAlgorithm)
                .compact();
    }
    public Boolean tokenValidation(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            if(redisUtil.hasKeyBlackList(token)){
                throw new RuntimeException("로그아웃 상태입니다.");
            }
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    public Boolean refreshTokenValidation(String token) {

        if(!tokenValidation(token)) return false;

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByAccountEmail(getEmailFromToken(token));

        return refreshToken.isPresent() && token.equals(refreshToken.get().getRefreshToken());
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Set<Authority> getAuthoritiesFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        Set<Authority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(Authority::new)
                .collect(Collectors.toSet());
        return authorities;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
    }
    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("Access_Token", accessToken);
    }

    public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
        response.setHeader("Refresh_Token", refreshToken);
    }


}
