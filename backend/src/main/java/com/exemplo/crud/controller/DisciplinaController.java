package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Disciplina;
import com.exemplo.crud.service.DisciplinaService;
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
@RequestMapping({"/api/disciplinas", "/disciplinas"})
@CrossOrigin(origins = "*")
@Tag(name = "Disciplinas", description = "CRUD de disciplinas")
@SecurityRequirement(name = "bearerAuth")
public class DisciplinaController {
    @Autowired
    private DisciplinaService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Listar todas as disciplinas")
    public List<Disciplina> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Buscar disciplina por ID")
    public Optional<Disciplina> getById(@Parameter(description = "ID da disciplina") @PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Criar disciplina", description = "Requer role PROFESSOR")
    public Disciplina create(@RequestBody Disciplina disciplina) { return service.save(disciplina); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Atualizar disciplina", description = "Requer role PROFESSOR")
    public Disciplina update(@Parameter(description = "ID da disciplina") @PathVariable Long id, @RequestBody Disciplina disciplina) {
        disciplina.setId(id);
        return service.save(disciplina);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Excluir disciplina", description = "Requer role PROFESSOR")
    public void delete(@Parameter(description = "ID da disciplina") @PathVariable Long id) { service.deleteById(id); }

    @GetMapping("/busca")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Busca avancada de disciplinas")
    public Page<Disciplina> buscaAvancada(@Parameter(description = "Filtrar por nome") @RequestParam(required = false) String nome,
                                          @Parameter(description = "Filtrar por status") @RequestParam(required = false) Boolean ativo,
                                          @Parameter(description = "Numero da pagina") @RequestParam(defaultValue = "0") int page,
                                          @Parameter(description = "Tamanho da pagina") @RequestParam(defaultValue = "10") int size,
                                          @Parameter(description = "Campo para ordenacao") @RequestParam(defaultValue = "id") String sortBy,
                                          @Parameter(description = "Direcao: asc ou desc") @RequestParam(defaultValue = "asc") String direction) {
        return service.buscarAvancado(nome, ativo, page, size, sortBy, direction);
    }
}
