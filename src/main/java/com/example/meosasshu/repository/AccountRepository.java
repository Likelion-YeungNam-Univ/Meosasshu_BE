package com.example.meosasshu.repository;


import com.example.meosasshu.entity.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByEmail(String email);
}
