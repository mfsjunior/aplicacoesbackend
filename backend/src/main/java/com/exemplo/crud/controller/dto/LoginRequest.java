package com.exemplo.crud.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para autenticação do usuário")
public record LoginRequest(
        @Schema(description = "Nome de usuário", example = "professor1", requiredMode = Schema.RequiredMode.REQUIRED)
        String username,

        @Schema(description = "Senha do usuário", example = "senha123", requiredMode = Schema.RequiredMode.REQUIRED)
        String password
) {}
