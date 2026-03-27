package com.example.security.entity;

// 데모용 간단한 사용자 엔티티
// 실제 프로젝트에서는 @Entity + JPA 매핑 추가
public class AppUser {

    private String username;
    private String password;
    private String role;

    public AppUser() {}

    public AppUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
