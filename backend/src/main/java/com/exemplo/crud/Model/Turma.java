package com.exemplo.crud.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "turma")
@Schema(description = "Entidade que representa uma turma")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID unico da turma", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome da turma", example = "Turma A - Manha")
    private String nome;

    @Schema(description = "Ano da turma", example = "2026")
    private int ano;

    @Schema(description = "Status ativo/inativo", example = "true")
    private boolean ativo;

    public Turma() {}

    public Turma(Long id, String nome, int ano, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
