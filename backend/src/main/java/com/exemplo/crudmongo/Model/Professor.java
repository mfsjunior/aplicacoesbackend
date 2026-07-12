package com.exemplo.crudmongo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 120, message = "Nome deve ter entre 3 e 120 caracteres")
    @Column(nullable = false, length = 120)
    private String nome;

    @NotNull(message = "Idade é obrigatória")
    @Min(value = 18, message = "Idade mínima para professor é 18")
    @Max(value = 120, message = "Idade máxima é 120")
    @Column(nullable = false)
    private int idade;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Column(nullable = false, unique = true, length = 180)
    private String email;

    @NotBlank(message = "Área é obrigatória")
    @Size(min = 2, max = 100, message = "Área deve ter entre 2 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String area;

    @Column(nullable = false)
    private boolean ativo;

    public Professor() {}

    public Professor(Long id, String nome, String area, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.area = area;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
