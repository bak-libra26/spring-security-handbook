package com.example.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

// OncePerRequestFilter: 요청당 한 번만 실행 보장
// (forward/include로 필터 중복 실행되는 문제 방지)
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        // Bearer 토큰이 없으면 다음 필터로 넘김
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        // TODO: 실제 프로젝트에서는 여기서 JWT 파싱 + 서명 검증
        // io.jsonwebtoken.Jwts.parser().verifyWith(key)...
        String username = extractUsername(token);

        if (username != null) {
            var auth = new UsernamePasswordAuthenticationToken(
                username, null, List.of());
            // SecurityContext에 인증 정보 설정
            // → 이후 @PreAuthorize 등에서 참조
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }

    private String extractUsername(String token) {
        // 데모용 단순 구현 — 실제로는 JWT 라이브러리 사용
        return token.length() > 10 ? "demo-user" : null;
    }
}
