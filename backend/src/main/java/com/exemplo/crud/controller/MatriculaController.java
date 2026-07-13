package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Matricula;
import com.exemplo.crud.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/matriculas", "/matriculas"})
@CrossOrigin(origins = "*")
public class MatriculaController {
    @Autowired
    private MatriculaService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    public List<Matricula> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    public Optional<Matricula> getById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    public Matricula create(@Valid @RequestBody Matricula matricula) { return service.save(matricula); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public Matricula update(@PathVariable Long id, @Valid @RequestBody Matricula matricula) {
        matricula.setId(id);
        return service.save(matricula);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public void delete(@PathVariable Long id) { service.deleteById(id); }

    @GetMapping("/busca")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    public Page<Matricula> buscaAvancada(@RequestParam(required = false) Long pessoaId,
                                         @RequestParam(required = false) Long cursoId,
                                         @RequestParam(required = false) String dataInicio,
                                         @RequestParam(required = false) String dataFim,
                                         @RequestParam(required = false) Boolean ativo,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String sortBy,
                                         @RequestParam(defaultValue = "asc") String direction) {
        return service.buscarAvancado(pessoaId, cursoId, dataInicio, dataFim, ativo, page, size, sortBy, direction);
    }
}
