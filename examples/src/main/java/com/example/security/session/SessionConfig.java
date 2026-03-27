package com.example.security.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 세션 관리 설정 예제 — 동시 로그인 제한 + Session Fixation 방어.
 *
 * [함정 1] maximumSessions(1)만 설정하면 기존 세션이 만료된다(후순위 로그인 우선).
 *   → maxSessionsPreventsLogin(true)로 바꿔야 "이미 로그인 중이면 새 로그인 차단"이 된다.
 *   → 면접에서 "동시 세션 제한의 두 가지 전략" 질문이 자주 나온다.
 *
 * [함정 2] Session Fixation 공격: 공격자가 미리 만든 세션 ID를 피해자에게 심는 공격.
 *   → Spring Security 기본값은 changeSessionId()로 방어해 준다.
 *   → 명시적으로 설정해 두면 "왜 이 설정이 있는지" 코드 리뷰에서 설명하기 좋다.
 */
@Configuration
public class SessionConfig {

    @Bean
    SecurityFilterChain sessionFilterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement(session -> session
                        // 동시 세션 1개 제한 — 이미 로그인 중이면 새 로그인 차단
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                        .and()
                        // Session Fixation 방어: 로그인 시 세션 ID를 새로 발급
                        .sessionFixation(fix -> fix.changeSessionId()))
                .build();
    }
}
