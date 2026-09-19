package com.exemplo.crud.controller;

import com.exemplo.crud.config.JwtUtil;
import com.exemplo.crud.dto.UsuarioResumoDTO;
import com.exemplo.crud.service.UsuarioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        try {
            String username = body.get("username");
            String password = body.get("password");
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String role = auth.getAuthorities().stream()
                    .findFirst()
                    .map(a -> a.getAuthority().replace("ROLE_", ""))
                    .orElse("USER");
            String token = jwtUtil.generateToken(username, role);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("role", role);
            return response;
        } catch (AuthenticationException e) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('PROFESSOR')")
    public Map<String, String> register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        String role = body.getOrDefault("role", "ALUNO");
        if (!role.equals("ALUNO") && !role.equals("PROFESSOR")) {
            throw new RuntimeException("Role inválida. Use ALUNO ou PROFESSOR.");
        }
        usuarioService.salvarUsuario(username, password, role);
        return Map.of("message", "Usuário registrado com sucesso");
    }

    @PostMapping("/register-aluno")
    public Map<String, String> registerAluno(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        usuarioService.registrarAluno(username, password);
        return Map.of("message", "Aluno registrado com sucesso", "role", "ALUNO");
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    public Map<String, String> me(Authentication authentication) {
        String username = authentication.getName();
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse("USER");

        return Map.of("username", username, "role", role);
    }

    @GetMapping("/usuarios")
    @PreAuthorize("hasRole('PROFESSOR')")
    public List<UsuarioResumoDTO> listarUsuarios() {
        return usuarioService.listarUsuariosResumo();
    }
}
