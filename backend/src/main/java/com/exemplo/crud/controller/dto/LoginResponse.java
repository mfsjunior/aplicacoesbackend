package com.exemplo.crud.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta da autenticação contendo o token JWT")
public record LoginResponse(
        @Schema(description = "Token JWT para autenticação nas requisições", 
                example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcm9mZXNzb3IxIiwicm9sZSI6IlBST0ZFU1NPUiIsImlhdCI6MTcyNjc0MDAwMH0.abc123")
        String token,

        @Schema(description = "Role do usuário autenticado", example = "PROFESSOR", 
                allowableValues = {"ALUNO", "PROFESSOR"})
        String role
) {}
