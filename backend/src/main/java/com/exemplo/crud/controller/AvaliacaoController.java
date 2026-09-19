package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Avaliacao;
import com.exemplo.crud.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/avaliacoes", "/avaliacoes"})
@CrossOrigin(origins = "*")
@Tag(name = "Avaliacoes", description = "CRUD de avaliacoes de alunos")
@SecurityRequirement(name = "bearerAuth")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Listar todas as avaliacoes")
    public List<Avaliacao> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Buscar avaliacao por ID")
    public Optional<Avaliacao> getById(@Parameter(description = "ID da avaliacao") @PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Criar avaliacao", description = "Requer role PROFESSOR")
    public Avaliacao create(@RequestBody Avaliacao avaliacao) { return service.save(avaliacao); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Atualizar avaliacao", description = "Requer role PROFESSOR")
    public Avaliacao update(@Parameter(description = "ID da avaliacao") @PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        avaliacao.setId(id);
        return service.save(avaliacao);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Excluir avaliacao", description = "Requer role PROFESSOR")
    public void delete(@Parameter(description = "ID da avaliacao") @PathVariable Long id) { service.deleteById(id); }

    @GetMapping("/busca")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Busca avancada de avaliacoes")
    public Page<Avaliacao> buscaAvancada(@Parameter(description = "ID da pessoa") @RequestParam(required = false) Long pessoaId,
                                         @Parameter(description = "ID da disciplina") @RequestParam(required = false) Long disciplinaId,
                                         @Parameter(description = "Nota minima") @RequestParam(required = false) Double notaMin,
                                         @Parameter(description = "Nota maxima") @RequestParam(required = false) Double notaMax,
                                         @Parameter(description = "Data inicio (yyyy-MM-dd)") @RequestParam(required = false) String dataInicio,
                                         @Parameter(description = "Data fim (yyyy-MM-dd)") @RequestParam(required = false) String dataFim,
                                         @Parameter(description = "Filtrar por status") @RequestParam(required = false) Boolean ativo,
                                         @Parameter(description = "Numero da pagina") @RequestParam(defaultValue = "0") int page,
                                         @Parameter(description = "Tamanho da pagina") @RequestParam(defaultValue = "10") int size,
                                         @Parameter(description = "Campo para ordenacao") @RequestParam(defaultValue = "id") String sortBy,
                                         @Parameter(description = "Direcao: asc ou desc") @RequestParam(defaultValue = "asc") String direction) {
        return service.buscarAvancado(pessoaId, disciplinaId, notaMin, notaMax, dataInicio, dataFim, ativo, page, size, sortBy, direction);
    }
}
