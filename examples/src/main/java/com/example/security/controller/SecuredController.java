package com.example.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// authenticated() 규칙이 적용되는 경로
@RestController
@RequestMapping("/api/secured")
public class SecuredController {

    // 인증된 사용자라면 누구나 접근 가능
    @GetMapping("/profile")
    public Map<String, Object> profile(
            @AuthenticationPrincipal UserDetails user) {
        return Map.of(
            "username", user.getUsername(),
            "authorities", user.getAuthorities()
        );
    }

    // ADMIN 권한만 접근 가능
    // @EnableMethodSecurity 없으면 이 어노테이션이 무시됨!
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public Map<String, String> admin() {
        return Map.of("message", "관리자 전용 페이지");
    }
}
