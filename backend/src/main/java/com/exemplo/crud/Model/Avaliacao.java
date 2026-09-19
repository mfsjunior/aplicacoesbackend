package com.exemplo.crud.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "avaliacao")
@Schema(description = "Entidade que representa uma avaliacao de uma pessoa em uma disciplina")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID unico da avaliacao", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "ID da pessoa avaliada", example = "1")
    private Long pessoaId;

    @Schema(description = "ID da disciplina", example = "1")
    private Long disciplinaId;

    @Schema(description = "Nota da avaliacao (0 a 10)", example = "8.5")
    private double nota;

    @Schema(description = "Data da avaliacao (formato: yyyy-MM-dd)", example = "2026-06-15")
    private String data;

    @Schema(description = "Status ativo/inativo", example = "true")
    private boolean ativo;

    public Avaliacao() {}

    public Avaliacao(Long id, Long pessoaId, Long disciplinaId, double nota, String data, boolean ativo) {
        this.id = id;
        this.pessoaId = pessoaId;
        this.disciplinaId = disciplinaId;
        this.nota = nota;
        this.data = data;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPessoaId() { return pessoaId; }
    public void setPessoaId(Long pessoaId) { this.pessoaId = pessoaId; }
    public Long getDisciplinaId() { return disciplinaId; }
    public void setDisciplinaId(Long disciplinaId) { this.disciplinaId = disciplinaId; }
    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
