package com.exemplo.crud.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "matricula")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "pessoaId é obrigatório")
    @Positive(message = "pessoaId deve ser positivo")
    @Column(nullable = false)
    private Long pessoaId;

    @NotNull(message = "cursoId é obrigatório")
    @Positive(message = "cursoId deve ser positivo")
    @Column(nullable = false)
    private Long cursoId;

    @NotBlank(message = "Data da matrícula é obrigatória")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Data deve estar no formato yyyy-MM-dd")
    @Column(nullable = false, length = 10)
    private String dataMatricula;

    @Column(nullable = false)
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
