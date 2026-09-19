package com.exemplo.crud.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta do registro de usuário")
public record RegisterResponse(
        @Schema(description = "Mensagem de confirmação do registro", example = "Usuário registrado com sucesso")
        String message
) {}
