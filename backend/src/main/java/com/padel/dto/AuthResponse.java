package com.padel.dto;
public class AuthResponse {
    private String token;
    private String email;
    private String role;
    public AuthResponse(String token, String email, String role) {
        this.token = token; this.email = email; this.role = role;
    }
    public String getToken() { return token; } public void setToken(String v) { this.token = v; }
    public String getEmail() { return email; } public void setEmail(String v) { this.email = v; }
    public String getRole() { return role; } public void setRole(String v) { this.role = v; }
}
