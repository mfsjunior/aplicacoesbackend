package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Curso;
import com.exemplo.crud.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
@CrossOrigin(origins = "*")
@Tag(name = "Cursos", description = "CRUD de cursos academicos")
@SecurityRequirement(name = "bearerAuth")
public class CursoController {
    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Listar todos os cursos")
    public List<Curso> listar() {
        return service.listarTodas();
    }

    @PostMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Criar curso", description = "Requer role PROFESSOR")
    public Curso criar(@RequestBody Curso curso) {
        return service.salvar(curso);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Atualizar curso", description = "Requer role PROFESSOR")
    public Curso atualizar(@Parameter(description = "ID do curso") @PathVariable Long id, @RequestBody Curso curso) {
        return service.atualizar(id, curso);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(summary = "Excluir curso", description = "Requer role PROFESSOR")
    public void excluir(@Parameter(description = "ID do curso") @PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/busca")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    @Operation(summary = "Busca avancada de cursos", description = "Busca com filtros, paginacao e ordenacao")
    public Page<Curso> buscaAvancada(@Parameter(description = "Filtrar por nome") @RequestParam(required = false) String nome,
                                     @Parameter(description = "Carga horaria minima") @RequestParam(required = false) Integer cargaHorariaMin,
                                     @Parameter(description = "Carga horaria maxima") @RequestParam(required = false) Integer cargaHorariaMax,
                                     @Parameter(description = "Filtrar por status") @RequestParam(required = false) Boolean ativo,
                                     @Parameter(description = "Numero da pagina") @RequestParam(defaultValue = "0") int page,
                                     @Parameter(description = "Tamanho da pagina") @RequestParam(defaultValue = "10") int size,
                                     @Parameter(description = "Campo para ordenacao") @RequestParam(defaultValue = "id") String sortBy,
                                     @Parameter(description = "Direcao: asc ou desc") @RequestParam(defaultValue = "asc") String direction) {
        return service.buscarAvancado(nome, cargaHorariaMin, cargaHorariaMax, ativo, page, size, sortBy, direction);
    }
}
