# Spring Security Handbook

> 인증 흐름부터 MSA 보안까지 — 공식 문서가 알려주지 않는 Why와 Pitfall을 다룹니다.

[![Blog](https://img.shields.io/badge/blog-bak--libra26.co.kr-green)](https://bak-libra26.co.kr)

## 시작하기

```bash
# Docker로 Redis (세션 저장소) 실행
cd docker
docker compose up -d redis redis-insight

# Redis CLI 접속
docker compose exec redis redis-cli
```

## 목차

### 기초 — 인증과 인가 개념

| # | 주제 | 블로그 |
|---|------|--------|
| 01 | 스프링 시큐리티: 스프링 애플리케이션 보안 솔루션 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/스프링%20시큐리티:%20스프링%20애플리케이션%20보안%20솔루션) |
| 02 | 스프링 시큐리티: 인증·인가와 세션 vs 토큰 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/스프링%20시큐리티:%20인증·인가와%20세션%20vs%20토큰) |
| 03 | 스프링 시큐리티: 인증 흐름과 동작 구조 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/스프링%20시큐리티:%20인증%20흐름과%20동작%20구조) |
| 04 | 스프링 시큐리티: 역할(Role)과 권한(Authority) | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/스프링%20시큐리티:%20역할(Role)과%20권한(Authority)) |

### 필터 체인과 인증 내부

| # | 주제 | 블로그 |
|---|------|--------|
| 05 | SecurityFilterChain은 어떻게 동작할까 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/스프링%20시큐리티:%20SecurityFilterChain은%20어떻게%20동작할까) |
| 06 | Spring Security 심화 — FilterChain, 인증 흐름, OAuth2 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/Spring%20Security%20심화%20—%20FilterChain,%20인증%20흐름,%20OAuth2) |
| 07 | AuthenticationManager — 인증 요청은 내부에서 어떻게 처리될까 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/AuthenticationManager%20—%20인증%20요청은%20내부에서%20어떻게%20처리될까) |
| 08 | UserDetailsService — 사용자 정보는 어디에서 어떻게 가져올까 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/UserDetailsService%20—%20사용자%20정보는%20어디에서%20어떻게%20가져올까) |
| 09 | Custom Filter — SecurityFilterChain에 나만의 필터를 추가하는 방법 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/Custom%20Filter%20—%20SecurityFilterChain에%20나만의%20필터를%20추가하는%20방법) |
| 10 | SecurityContext 전파 — 인증 정보는 요청 처리 동안 어떻게 유지될까 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/SecurityContext%20전파%20—%20인증%20정보는%20요청%20처리%20동안%20어떻게%20유지될까) |

### 비밀번호와 세션

| # | 주제 | 블로그 |
|---|------|--------|
| 11 | PasswordEncoder — 비밀번호는 어떻게 안전하게 저장할까 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/PasswordEncoder%20—%20비밀번호는%20어떻게%20안전하게%20저장할까) |
| 12 | Session 관리 — 동시 로그인 제한과 세션 고정 공격 방어 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/Session%20관리%20—%20동시%20로그인%20제한과%20세션%20고정%20공격%20방어) |
| 13 | remember-me, 자동 로그인은 어떻게 동작할까 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/스프링%20시큐리티:%20remember-me,%20자동%20로그인은%20어떻게%20동작할까) |

### JWT 인증

| # | 주제 | 블로그 |
|---|------|--------|
| 14 | JWT(JSON Web Token)는 무엇이고, 왜 쓰는가 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/스프링%20시큐리티:%20JWT(JSON%20Web%20Token)는%20무엇이고,%20왜%20쓰는가) |
| 15 | JWT(JSON Web Token) 인증 구현하기 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/스프링%20시큐리티:%20JWT(JSON%20Web%20Token)%20인증%20구현하기) |
| 16 | JWT 만료·재발급·로그아웃 전략 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/스프링%20시큐리티:%20JWT%20만료·재발급·로그아웃%20전략) |

### 보안 정책

| # | 주제 | 블로그 |
|---|------|--------|
| 17 | CORS는 왜 필요하고, 어떻게 동작할까 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/스프링%20시큐리티:%20CORS는%20왜%20필요하고,%20어떻게%20동작할까) |
| 18 | CORS 심화 — 브라우저의 보안 정책과 스프링의 대응 방식 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/CORS%20심화%20—%20브라우저의%20보안%20정책과%20스프링의%20대응%20방식) |
| 19 | CSRF 보호는 왜 필요하고, 어떻게 동작할까 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/스프링%20시큐리티:%20CSRF%20보호는%20왜%20필요하고,%20어떻게%20동작할까) |

### OAuth2와 권한 제어

| # | 주제 | 블로그 |
|---|------|--------|
| 20 | OAuth2 로그인과 리소스 서버 개념 정리 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/스프링%20시큐리티:%20OAuth2%20로그인과%20리소스%20서버%20개념%20정리) |
| 21 | Method Security — URL이 아닌 메서드 단위로 권한을 제어하는 방법 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/Method%20Security%20—%20URL이%20아닌%20메서드%20단위로%20권한을%20제어하는%20방법) |

### 예외 처리와 테스트

| # | 주제 | 블로그 |
|---|------|--------|
| 22 | Exception Handling — 인증·인가 실패 시 어떤 일이 벌어질까 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/Exception%20Handling%20—%20인증·인가%20실패%20시%20어떤%20일이%20벌어질까) |
| 23 | Security Testing — 보안이 적용된 API를 어떻게 테스트할까 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/Security%20Testing%20—%20보안이%20적용된%20API를%20어떻게%20테스트할까) |

### 심화/운영

| # | 주제 | 블로그 |
|---|------|--------|
| 24 | Spring Security + Redis 세션 클러스터링 — 다중 서버에서 세션 공유하기 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/Spring%20Security%20+%20Redis%20세션%20클러스터링%20—%20다중%20서버에서%20세션%20공유하기) |
| 25 | API Gateway와 Security — MSA에서 인증을 처리하는 패턴 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/API%20Gateway와%20Security%20—%20MSA에서%20인증을%20처리하는%20패턴) |
| 26 | 스프링 시큐리티 트러블슈팅 — 흔한 버그와 해결법 | [읽기](https://bak-libra26.co.kr/posts/개발/프레임워크/스프링/스프링%20시큐리티/스프링%20시큐리티%20트러블슈팅%20—%20흔한%20버그와%20해결법) |

## Docker 환경

```bash
cd docker
docker compose up -d     # Spring Boot + Redis + RedisInsight 실행
docker compose down      # 종료
docker compose down -v   # 종료 + 데이터 삭제
```

- **Spring Boot App**: localhost:8080
- **Redis**: localhost:6379
- **RedisInsight** (GUI): http://localhost:5540

## 이 핸드북의 차별점

1. **Why** — 공식 문서가 "이렇게 쓰세요"라면, 여기서는 "왜 이렇게 동작하는지" 설명합니다
2. **Pitfall** — 실무에서 터지는 함정을 모든 글에 포함합니다
3. **한국어** — 한국어로 가장 체계적인 Spring Security 시리즈를 목표로 합니다

## 저자

**sim.junghun** — Backend Engineer
- Blog: [bak-libra26.co.kr](https://bak-libra26.co.kr)
- GitHub: [@bak-libra26](https://github.com/bak-libra26)
