package com.exemplo.crud.controller;

import com.exemplo.crud.config.JwtUtil;
import com.exemplo.crud.controller.dto.LoginRequest;
import com.exemplo.crud.controller.dto.LoginResponse;
import com.exemplo.crud.controller.dto.RegisterRequest;
import com.exemplo.crud.controller.dto.RegisterResponse;
import com.exemplo.crud.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticacao", description = "Endpoints para registro de usuarios e autenticacao via JWT")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @Operation(
            summary = "Autenticar usuario",
            description = "Realiza o login do usuario e retorna um token JWT valido por 1 hora. "
                    + "O token deve ser enviado no header Authorization como Bearer Token nas requisicoes subsequentes.",
            security = {}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "401", description = "Usuario ou senha invalidos",
                    content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
            String role = auth.getAuthorities().stream()
                    .findFirst()
                    .map(a -> a.getAuthority().replace("ROLE_", ""))
                    .orElse("USER");
            String token = jwtUtil.generateToken(request.username(), role);
            return ResponseEntity.ok(new LoginResponse(token, role));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Usuario ou senha invalidos"));
        }
    }

    @Operation(
            summary = "Registrar novo usuario",
            description = "Cria um novo usuario no sistema. A senha e armazenada com hash BCrypt. "
                    + "Roles permitidas: ALUNO, PROFESSOR.",
            security = {}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterResponse.class))),
            @ApiResponse(responseCode = "400", description = "Role invalida ou dados incompletos",
                    content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        String role = request.role() != null ? request.role() : "ALUNO";
        if (!role.equals("ALUNO") && !role.equals("PROFESSOR")) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Role invalida. Use ALUNO ou PROFESSOR."));
        }
        usuarioService.salvarUsuario(request.username(), request.password(), role);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RegisterResponse("Usuario registrado com sucesso"));
    }
}
