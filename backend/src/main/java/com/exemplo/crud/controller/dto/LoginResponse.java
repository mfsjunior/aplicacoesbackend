package com.exemplo.crud.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta da autenticacao contendo o token JWT")
public class LoginResponse {

    @Schema(description = "Token JWT para autenticacao nas requisicoes",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mZXNzb3IxIn0.abc123")
    private String token;

    @Schema(description = "Role do usuario autenticado", example = "PROFESSOR")
    private String role;

    public LoginResponse() {}

    public LoginResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
