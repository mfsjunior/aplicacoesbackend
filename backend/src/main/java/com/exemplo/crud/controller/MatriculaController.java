package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Matricula;
import com.exemplo.crud.service.MatriculaService;
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
@RequestMapping({"/api/matriculas", "/matriculas"})
@CrossOrigin(origins = "*")
@Tag(name = "Matriculas", description = "CRUD de matriculas de alunos em cursos")
@SecurityRequirement(name = "bearerAuth")
public class MatriculaController {
    @Autowired
    private MatriculaService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Listar todas as matriculas")
    public List<Matricula> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Buscar matricula por ID")
    public Optional<Matricula> getById(@Parameter(description = "ID da matricula") @PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Criar matricula", description = "Requer role PROFESSOR")
    public Matricula create(@RequestBody Matricula matricula) { return service.save(matricula); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Atualizar matricula", description = "Requer role PROFESSOR")
    public Matricula update(@Parameter(description = "ID da matricula") @PathVariable Long id, @RequestBody Matricula matricula) {
        matricula.setId(id);
        return service.save(matricula);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Excluir matricula", description = "Requer role PROFESSOR")
    public void delete(@Parameter(description = "ID da matricula") @PathVariable Long id) { service.deleteById(id); }

    @GetMapping("/busca")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Busca avancada de matriculas")
    public Page<Matricula> buscaAvancada(@Parameter(description = "ID da pessoa") @RequestParam(required = false) Long pessoaId,
                                         @Parameter(description = "ID do curso") @RequestParam(required = false) Long cursoId,
                                         @Parameter(description = "Data inicio (yyyy-MM-dd)") @RequestParam(required = false) String dataInicio,
                                         @Parameter(description = "Data fim (yyyy-MM-dd)") @RequestParam(required = false) String dataFim,
                                         @Parameter(description = "Filtrar por status") @RequestParam(required = false) Boolean ativo,
                                         @Parameter(description = "Numero da pagina") @RequestParam(defaultValue = "0") int page,
                                         @Parameter(description = "Tamanho da pagina") @RequestParam(defaultValue = "10") int size,
                                         @Parameter(description = "Campo para ordenacao") @RequestParam(defaultValue = "id") String sortBy,
                                         @Parameter(description = "Direcao: asc ou desc") @RequestParam(defaultValue = "asc") String direction) {
        return service.buscarAvancado(pessoaId, cursoId, dataInicio, dataFim, ativo, page, size, sortBy, direction);
    }
}
