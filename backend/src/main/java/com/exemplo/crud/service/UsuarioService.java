package com.exemplo.crud.service;

import com.exemplo.crud.Model.Usuario;
import com.exemplo.crud.dto.UsuarioResumoDTO;
import com.exemplo.crud.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRole())
                .build();
    }

    public Usuario salvarUsuario(String username, String password, String role) {
        if (usuarioRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username j� cadastrado");
        }
        Usuario usuario = new Usuario(username, passwordEncoder.encode(password), role);
        return usuarioRepository.save(usuario);
    }

    public Usuario registrarAluno(String username, String password) {
        return salvarUsuario(username, password, "ALUNO");
    }

    public List<UsuarioResumoDTO> listarUsuariosResumo() {
        return usuarioRepository.findAllByOrderByUsernameAsc().stream()
                .map(usuario -> new UsuarioResumoDTO(usuario.getUsername(), usuario.getRole()))
                .toList();
    }
}
