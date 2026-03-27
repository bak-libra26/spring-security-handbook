package com.example.security.pitfall;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

// ⚠️ CORS 설정 실수 모음 — 실제로는 SecurityConfig에 통합
// 이 클래스는 @Configuration 아님 (데모용)
public class CorsDemo {

    // ❌ 실수: Spring MVC의 @CrossOrigin만 설정
    // → Security 필터가 먼저 요청을 차단하므로 무의미
    // → 반드시 SecurityFilterChain에서 .cors() 설정 필요

    // ✅ 올바른 방법: CorsConfigurationSource 빈 등록
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // ❌ 프로덕션에서 "*" 사용 금지
        // config.setAllowedOrigins(List.of("*"));

        // ✅ 허용할 출처를 명시적으로 지정
        config.setAllowedOrigins(List.of("https://myapp.com"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        // ⚠️ credentials: true + origin: "*" 조합은 브라우저가 거부함
        config.setAllowCredentials(true);

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);
        return source;
    }
}
