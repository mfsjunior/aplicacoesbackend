package com.exemplo.crud.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para registro de novo usuario")
public class RegisterRequest {

    @Schema(description = "Nome de usuario (deve ser unico)", example = "novoaluno1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(description = "Senha do usuario", example = "senha123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(description = "Role do usuario (ALUNO ou PROFESSOR). Padrao: ALUNO", example = "ALUNO",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String role;

    public RegisterRequest() {}

    public RegisterRequest(String username, String password, String role) {
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
