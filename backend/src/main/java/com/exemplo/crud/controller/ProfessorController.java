package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Professor;
import com.exemplo.crud.service.ProfessorService;
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
@RequestMapping({"/api/professores", "/professores"})
@CrossOrigin(origins = "*")
@Tag(name = "Professores", description = "CRUD de professores")
@SecurityRequirement(name = "bearerAuth")
public class ProfessorController {
    @Autowired
    private ProfessorService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Listar todos os professores")
    public List<Professor> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Buscar professor por ID")
    public Optional<Professor> getById(@Parameter(description = "ID do professor") @PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Criar professor", description = "Requer role PROFESSOR")
    public Professor create(@RequestBody Professor professor) { return service.save(professor); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Atualizar professor", description = "Requer role PROFESSOR")
    public Professor update(@Parameter(description = "ID do professor") @PathVariable Long id, @RequestBody Professor professor) {
        professor.setId(id);
        return service.save(professor);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Excluir professor", description = "Requer role PROFESSOR")
    public void delete(@Parameter(description = "ID do professor") @PathVariable Long id) { service.deleteById(id); }

    @GetMapping("/busca")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Busca avancada de professores")
    public Page<Professor> buscaAvancada(@Parameter(description = "Filtrar por nome") @RequestParam(required = false) String nome,
                                         @Parameter(description = "Filtrar por email") @RequestParam(required = false) String email,
                                         @Parameter(description = "Filtrar por area") @RequestParam(required = false) String area,
                                         @Parameter(description = "Idade minima") @RequestParam(required = false) Integer idadeMin,
                                         @Parameter(description = "Idade maxima") @RequestParam(required = false) Integer idadeMax,
                                         @Parameter(description = "Filtrar por status") @RequestParam(required = false) Boolean ativo,
                                         @Parameter(description = "Numero da pagina") @RequestParam(defaultValue = "0") int page,
                                         @Parameter(description = "Tamanho da pagina") @RequestParam(defaultValue = "10") int size,
                                         @Parameter(description = "Campo para ordenacao") @RequestParam(defaultValue = "id") String sortBy,
                                         @Parameter(description = "Direcao: asc ou desc") @RequestParam(defaultValue = "asc") String direction) {
        return service.buscarAvancado(nome, email, area, idadeMin, idadeMax, ativo, page, size, sortBy, direction);
    }
}
