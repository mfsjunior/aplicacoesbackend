package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Curso;
import com.exemplo.crud.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
@CrossOrigin(origins = "*")
public class CursoController {
    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    public List<Curso> listar() {
        return service.listarTodas();
    }

    @PostMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    public Curso criar(@Valid @RequestBody Curso curso) {
        return service.salvar(curso);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public Curso atualizar(@PathVariable Long id, @Valid @RequestBody Curso curso) {
        return service.atualizar(id, curso);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/busca")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    public Page<Curso> buscaAvancada(@RequestParam(required = false) String nome,
                                     @RequestParam(required = false) Integer cargaHorariaMin,
                                     @RequestParam(required = false) Integer cargaHorariaMax,
                                     @RequestParam(required = false) Boolean ativo,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sortBy,
                                     @RequestParam(defaultValue = "asc") String direction) {
        return service.buscarAvancado(nome, cargaHorariaMin, cargaHorariaMax, ativo, page, size, sortBy, direction);
    }
}
