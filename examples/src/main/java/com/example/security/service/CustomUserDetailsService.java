package com.example.security.service;

import com.example.security.entity.AppUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// UserDetailsService 구현: Spring Security가 인증 시 자동 호출
// → DB 조회로 교체할 때 이 클래스만 수정하면 됨
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PasswordEncoder encoder;
    private final Map<String, AppUser> store = new ConcurrentHashMap<>();

    public CustomUserDetailsService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @PostConstruct
    void init() {
        // 데모용 인메모리 사용자 — 실제로는 JPA Repository 사용
        store.put("user", new AppUser("user",
            encoder.encode("password"), "ROLE_USER"));
        store.put("admin", new AppUser("admin",
            encoder.encode("admin"), "ROLE_ADMIN"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser u = store.get(username);
        if (u == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(u.getUsername(), u.getPassword(),
            List.of(new SimpleGrantedAuthority(u.getRole())));
    }
}
