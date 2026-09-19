package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Turma;
import com.exemplo.crud.service.TurmaService;
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
@RequestMapping({"/api/turmas", "/turmas"})
@CrossOrigin(origins = "*")
@Tag(name = "Turmas", description = "CRUD de turmas")
@SecurityRequirement(name = "bearerAuth")
public class TurmaController {
    @Autowired
    private TurmaService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Listar todas as turmas")
    public List<Turma> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Buscar turma por ID")
    public Optional<Turma> getById(@Parameter(description = "ID da turma") @PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Criar turma", description = "Requer role PROFESSOR")
    public Turma create(@RequestBody Turma turma) { return service.save(turma); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Atualizar turma", description = "Requer role PROFESSOR")
    public Turma update(@Parameter(description = "ID da turma") @PathVariable Long id, @RequestBody Turma turma) {
        turma.setId(id);
        return service.save(turma);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Excluir turma", description = "Requer role PROFESSOR")
    public void delete(@Parameter(description = "ID da turma") @PathVariable Long id) { service.deleteById(id); }

    @GetMapping("/busca")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Busca avancada de turmas")
    public Page<Turma> buscaAvancada(@Parameter(description = "Filtrar por nome") @RequestParam(required = false) String nome,
                                     @Parameter(description = "Ano minimo") @RequestParam(required = false) Integer anoMin,
                                     @Parameter(description = "Ano maximo") @RequestParam(required = false) Integer anoMax,
                                     @Parameter(description = "Filtrar por status") @RequestParam(required = false) Boolean ativo,
                                     @Parameter(description = "Numero da pagina") @RequestParam(defaultValue = "0") int page,
                                     @Parameter(description = "Tamanho da pagina") @RequestParam(defaultValue = "10") int size,
                                     @Parameter(description = "Campo para ordenacao") @RequestParam(defaultValue = "id") String sortBy,
                                     @Parameter(description = "Direcao: asc ou desc") @RequestParam(defaultValue = "asc") String direction) {
        return service.buscarAvancado(nome, anoMin, anoMax, ativo, page, size, sortBy, direction);
    }
}
