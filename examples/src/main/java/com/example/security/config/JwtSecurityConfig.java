package com.example.security.config;

import com.example.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// JWT 모드로 전환하려면:
// 1. BasicSecurityConfig의 @Configuration 주석 처리
// 2. 아래 @Configuration 주석 해제
// @Configuration
public class JwtSecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public JwtSecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            // JWT는 무상태 → 서버 세션 생성하지 않음
            .sessionManagement(sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated()
            )
            // UsernamePasswordAuthenticationFilter 앞에 JWT 필터 삽입
            // → 토큰 검증 후 SecurityContext에 인증 정보 설정
            .addFilterBefore(jwtFilter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
