# Spring Security Handbook Examples

Spring Boot 3.4 + Security 6 기반 실행 가능한 예제 프로젝트.

## 실행
```bash
./gradlew bootRun
```

## 엔드포인트 테스트
```bash
# 공개 API
curl http://localhost:8080/api/public/health

# 인증 필요 (401 반환)
curl http://localhost:8080/api/secured/profile

# Basic Auth로 인증
curl -u user:password http://localhost:8080/api/secured/profile
```

## 구성
| 파일 | 설명 |
|------|------|
| `BasicSecurityConfig` | SecurityFilterChain 기본 설정 |
| `JwtSecurityConfig` | JWT 기반 설정 (주석 처리, 전환 가능) |
| `JwtAuthenticationFilter` | OncePerRequestFilter 기반 JWT 검증 |
| `SelfInvocationDemo` | @PreAuthorize 내부 호출 함정 |
| `CorsDemo` | CORS 설정 실수 재현 |

## JWT 모드 전환
1. `BasicSecurityConfig`의 `@Configuration` 주석 처리
2. `JwtSecurityConfig`의 `@Configuration` 주석 해제
