package com.example.security.pitfall;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

// ⚠️ 함정: 같은 클래스 내부에서 @PreAuthorize 메서드 호출 시
// 프록시를 거치지 않아 보안 검사가 무시됨
@Service
public class SelfInvocationDemo {

    // 외부에서 직접 호출하면 → 프록시 경유 → 권한 검사 O
    @PreAuthorize("hasRole('ADMIN')")
    public String adminOnly() {
        return "관리자 전용 데이터";
    }

    // ❌ 같은 클래스 내부 호출 → this.adminOnly() → 프록시 우회
    // → @PreAuthorize 무시됨!
    public String dangerousCall() {
        // 이 호출은 권한 검사 없이 실행됨
        return this.adminOnly();
    }

    // ✅ 해결법 1: 별도 서비스로 분리
    // ✅ 해결법 2: self-injection 패턴
    //   @Autowired
    //   private SelfInvocationDemo self;
    //   public String safeCall() {
    //       return self.adminOnly(); // 프록시 경유
    //   }
}
