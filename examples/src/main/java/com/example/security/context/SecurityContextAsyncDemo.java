package com.example.security.context;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Async에서 SecurityContext가 전파되지 않는 문제 + 해결 방법.
 *
 * [함정] @Async 메서드는 별도 스레드에서 실행되므로
 *   SecurityContextHolder.getContext()가 빈 값(anonymous)을 반환한다.
 *   → "분명 인증된 사용자인데 왜 권한이 없지?" 버그가 발생한다.
 *
 * [해결 1] SecurityContextHolder.setStrategyName(MODE_INHERITABLETHREADLOCAL)
 *   → 간단하지만, 스레드 풀 재사용 시 이전 사용자 컨텍스트가 남는 보안 위험이 있다.
 *
 * [해결 2] DelegatingSecurityContextExecutorService 사용 (권장)
 *   → 작업 제출 시점의 SecurityContext를 캡처하여 실행 스레드에 전달한다.
 *   → 스레드 풀을 재사용해도 안전하다.
 */
@Service
public class SecurityContextAsyncDemo {

    // ── 문제 재현: @Async 메서드에서 인증 정보가 null ──
    @Async
    public void brokenAsyncCall() {
        // 이 시점에 SecurityContext가 비어 있다 (다른 스레드이므로)
        var auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("인증 정보: " + auth); // null 또는 anonymous
    }

    // ── 해결: DelegatingSecurityContextExecutorService로 감싸기 ──
    private final ExecutorService safeExecutor =
            new DelegatingSecurityContextExecutorService(
                    Executors.newFixedThreadPool(4));

    public void safeAsyncCall() {
        safeExecutor.submit(() -> {
            // 제출 시점의 SecurityContext가 자동으로 전파된다
            var auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("안전하게 전파된 인증 정보: " + auth);
        });
    }
}
