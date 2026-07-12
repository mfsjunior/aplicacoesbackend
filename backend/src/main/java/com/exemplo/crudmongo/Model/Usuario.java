package com.exemplo.crudmongo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username é obrigatório")
    @Size(min = 3, max = 50, message = "Username deve ter entre 3 e 50 caracteres")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 120, message = "Senha deve ter entre 6 e 120 caracteres")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Role é obrigatória")
    @Pattern(regexp = "^(ALUNO|PROFESSOR)$", message = "Role deve ser ALUNO ou PROFESSOR")
    @Column(nullable = false, length = 20)
    private String role;

    public Usuario() {}

    public Usuario(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
