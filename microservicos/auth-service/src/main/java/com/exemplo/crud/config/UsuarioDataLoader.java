package com.exemplo.crud.config;

import com.exemplo.crud.repository.UsuarioRepository;
import com.exemplo.crud.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioDataLoader {

    @Bean
    CommandLineRunner seedUsuarios(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        return args -> {
            if (usuarioRepository.findByUsername("professor").isEmpty()) {
                usuarioService.salvarUsuario("professor", "prof123", "PROFESSOR");
                System.out.println("? Usuário 'professor' criado (role: PROFESSOR)");
            }
            if (usuarioRepository.findByUsername("aluno").isEmpty()) {
                usuarioService.salvarUsuario("aluno", "aluno123", "ALUNO");
                System.out.println("? Usuário 'aluno' criado (role: ALUNO)");
            }
        };
    }
}
