package com.exemplo.crud.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "pessoaId é obrigatório")
    @Positive(message = "pessoaId deve ser positivo")
    @Column(nullable = false)
    private Long pessoaId;

    @NotNull(message = "disciplinaId é obrigatório")
    @Positive(message = "disciplinaId deve ser positivo")
    @Column(nullable = false)
    private Long disciplinaId;

    @DecimalMin(value = "0.0", message = "Nota mínima é 0")
    @DecimalMax(value = "10.0", message = "Nota máxima é 10")
    @Column(nullable = false)
    private double nota;

    @NotBlank(message = "Data é obrigatória")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Data deve estar no formato yyyy-MM-dd")
    @Column(nullable = false, length = 10)
    private String data;

    @Column(nullable = false)
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
