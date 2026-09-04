package com.exemplo.crud.service;

import com.exemplo.crud.Model.Usuario;
import com.exemplo.crud.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvarUsuario(String username, String password, String role) {
        Usuario usuario = new Usuario(username, password, role);
        return usuarioRepository.save(usuario);
    }
}
