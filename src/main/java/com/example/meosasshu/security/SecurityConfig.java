package com.example.meosasshu.security;

import com.example.meosasshu.security.jwt.JwtAuthFilter;
import com.example.meosasshu.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtUtil jwtUtil;

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public WebSecurityCustomizer ignoringCustomizer() {
            return (web) -> web.ignoring().requestMatchers("/h2-console/**");
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http.cors();
            http.csrf().disable();

            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.authorizeRequests().requestMatchers("/api/account/**","/swagger-ui/**", "/v3/api-docs/**","/"
                    ,"/payment").permitAll()
                    .requestMatchers("/api/v1/admin/**").hasAnyAuthority("ROLE_ADMIN")
                    .requestMatchers("/api/blog/**","/api/category/**", "/api/post/**","/api/tag/**","/api/comment/**").permitAll()
//                    .anyRequest().authenticated()
                    .anyRequest().permitAll()
                    .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

            return http.build();

        }
    }

