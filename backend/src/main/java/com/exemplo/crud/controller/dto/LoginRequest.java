package com.exemplo.crud.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para autenticacao do usuario")
public class LoginRequest {

    @Schema(description = "Nome de usuario", example = "professor1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(description = "Senha do usuario", example = "senha123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    public LoginRequest() {}

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
