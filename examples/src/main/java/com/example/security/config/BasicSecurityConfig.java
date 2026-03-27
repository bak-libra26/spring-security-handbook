package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Security 6부터 WebSecurityConfigurerAdapter 제거됨
// → SecurityFilterChain 빈 등록 방식으로 전환
@Configuration
@EnableMethodSecurity // @PreAuthorize 활성화에 필요
public class BasicSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // REST API 서버이므로 CSRF 비활성화
            // ⚠️ 브라우저 폼 기반이면 반드시 활성화해야 함
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // 공개 엔드포인트: 인증 없이 접근 가능
                .requestMatchers("/api/public/**").permitAll()
                // 그 외 모든 요청은 인증 필요
                .anyRequest().authenticated()
            )
            // HTTP Basic 인증 활성화 (테스트/개발용)
            .httpBasic(basic -> {});

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // bcrypt: 솔트 자동 생성 + 느린 해싱으로 브루트포스 방어
        return new BCryptPasswordEncoder();
    }
}
