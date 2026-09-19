package com.exemplo.crud.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para registro de novo usuário")
public record RegisterRequest(
        @Schema(description = "Nome de usuário (deve ser único)", example = "novoaluno1", requiredMode = Schema.RequiredMode.REQUIRED)
        String username,

        @Schema(description = "Senha do usuário", example = "senha123", requiredMode = Schema.RequiredMode.REQUIRED)
        String password,

        @Schema(description = "Role do usuário (ALUNO ou PROFESSOR). Padrão: ALUNO", 
                example = "ALUNO", allowableValues = {"ALUNO", "PROFESSOR"}, 
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String role
) {}
