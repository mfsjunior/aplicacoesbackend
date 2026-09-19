package com.exemplo.crud.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta do registro de usuario")
public class RegisterResponse {

    @Schema(description = "Mensagem de confirmacao do registro", example = "Usuario registrado com sucesso")
    private String message;

    public RegisterResponse() {}

    public RegisterResponse(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
