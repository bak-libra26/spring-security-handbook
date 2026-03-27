package com.example.security.csrf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * CSRF 설정 예제 — 언제 끄고, 언제 켜야 하는지 정리.
 *
 * [함정 1] REST API만 제공하는 서버에서 CSRF를 켜 놓으면
 *   → POST/PUT 요청마다 403이 떨어져서 "왜 안 되지?" 삽질한다.
 *   → JWT 같은 토큰 기반 인증은 쿠키를 안 쓰므로 CSRF가 불필요하다.
 *
 * [함정 2] 반대로 세션+쿠키 기반 인증(폼 로그인, 서버사이드 렌더링)인데
 *   CSRF를 꺼 버리면 공격자가 사용자 권한으로 요청을 날릴 수 있다.
 *   → 이 경우 반드시 CSRF를 켜고 CookieCsrfTokenRepository 등을 사용한다.
 */
@Configuration
public class CsrfConfigExample {

    // ── 케이스 1: JWT(토큰) 기반 → CSRF 비활성화 ──
    @Bean
    SecurityFilterChain statelessChain(HttpSecurity http) throws Exception {
        return http
                // 세션을 아예 안 쓰므로 CSRF도 끈다
                .csrf(csrf -> csrf.disable())
                .build();
    }

    // ── 케이스 2: 세션+쿠키 기반 → CSRF 활성화 (SPA 대응) ──
    // @Bean  // 동시에 두 체인을 등록하면 충돌하므로, 실습 시 하나만 활성화할 것
    SecurityFilterChain sessionChain(HttpSecurity http) throws Exception {
        return http
                // SPA에서 XSRF-TOKEN 쿠키를 읽어 헤더로 보내는 패턴
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .build();
    }
}
