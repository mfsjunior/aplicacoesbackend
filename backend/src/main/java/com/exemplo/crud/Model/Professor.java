package com.exemplo.crud.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "professor")
@Schema(description = "Entidade que representa um professor")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID unico do professor", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome completo do professor", example = "Maria Oliveira")
    private String nome;

    @Schema(description = "Idade do professor", example = "45")
    private int idade;

    @Schema(description = "Email do professor", example = "maria.oliveira@universidade.com")
    private String email;

    @Schema(description = "Area de atuacao do professor", example = "Ciencia da Computacao")
    private String area;

    @Schema(description = "Status ativo/inativo", example = "true")
    private boolean ativo;

    public Professor() {}

    public Professor(Long id, String nome, String area, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.area = area;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
