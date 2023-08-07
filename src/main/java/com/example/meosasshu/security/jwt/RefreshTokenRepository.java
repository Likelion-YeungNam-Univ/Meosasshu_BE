package com.example.meosasshu.security.jwt;


import com.example.meosasshu.security.jwt.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByAccountEmail(String email);

    void deleteRefreshTokenByAccountEmail(String email);
}
