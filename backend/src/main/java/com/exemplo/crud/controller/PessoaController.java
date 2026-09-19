package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Pessoa;
import com.exemplo.crud.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
@CrossOrigin(origins = "*")
@Tag(name = "Pessoas", description = "CRUD de pessoas/alunos")
@SecurityRequirement(name = "bearerAuth")
public class PessoaController {
    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Listar todas as pessoas", description = "Retorna a lista completa de pessoas cadastradas")
    public List<Pessoa> listar() {
        return service.listarTodas();
    }

    @PostMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Criar pessoa", description = "Cadastra uma nova pessoa. Requer role PROFESSOR")
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return service.salvar(pessoa);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Atualizar pessoa", description = "Atualiza os dados de uma pessoa existente. Requer role PROFESSOR")
    public Pessoa atualizar(@Parameter(description = "ID da pessoa") @PathVariable Long id, @RequestBody Pessoa pessoa) {
        return service.atualizar(id, pessoa);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Excluir pessoa", description = "Remove uma pessoa do sistema. Requer role PROFESSOR")
    public void excluir(@Parameter(description = "ID da pessoa") @PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/busca")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Busca avancada de pessoas", description = "Busca com filtros, paginacao e ordenacao")
    public Page<Pessoa> buscaAvancada(@Parameter(description = "Filtrar por nome") @RequestParam(required = false) String nome,
                                      @Parameter(description = "Filtrar por email") @RequestParam(required = false) String email,
                                      @Parameter(description = "Idade minima") @RequestParam(required = false) Integer idadeMin,
                                      @Parameter(description = "Idade maxima") @RequestParam(required = false) Integer idadeMax,
                                      @Parameter(description = "Filtrar por status") @RequestParam(required = false) Boolean ativo,
                                      @Parameter(description = "Numero da pagina") @RequestParam(defaultValue = "0") int page,
                                      @Parameter(description = "Tamanho da pagina") @RequestParam(defaultValue = "10") int size,
                                      @Parameter(description = "Campo para ordenacao") @RequestParam(defaultValue = "id") String sortBy,
                                      @Parameter(description = "Direcao: asc ou desc") @RequestParam(defaultValue = "asc") String direction) {
        return service.buscarAvancado(nome, email, idadeMin, idadeMax, ativo, page, size, sortBy, direction);
    }
}
