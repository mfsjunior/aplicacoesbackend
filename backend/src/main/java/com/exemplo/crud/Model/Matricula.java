package com.exemplo.crud.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "matricula")
@Schema(description = "Entidade que representa a matricula de uma pessoa em um curso")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID unico da matricula", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "ID da pessoa matriculada", example = "1")
    private Long pessoaId;

    @Schema(description = "ID do curso", example = "1")
    private Long cursoId;

    @Schema(description = "Data da matricula (formato: yyyy-MM-dd)", example = "2026-03-15")
    private String dataMatricula;

    @Schema(description = "Status ativo/inativo", example = "true")
    private boolean ativo;

    public Matricula() {}

    public Matricula(Long id, Long pessoaId, Long cursoId, String dataMatricula, boolean ativo) {
        this.id = id;
        this.pessoaId = pessoaId;
        this.cursoId = cursoId;
        this.dataMatricula = dataMatricula;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPessoaId() { return pessoaId; }
    public void setPessoaId(Long pessoaId) { this.pessoaId = pessoaId; }
    public Long getCursoId() { return cursoId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }
    public String getDataMatricula() { return dataMatricula; }
    public void setDataMatricula(String dataMatricula) { this.dataMatricula = dataMatricula; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
