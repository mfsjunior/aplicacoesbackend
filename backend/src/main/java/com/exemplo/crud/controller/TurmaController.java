package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Turma;
import com.exemplo.crud.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/turmas", "/turmas"})
@CrossOrigin(origins = "*")
public class TurmaController {
    @Autowired
    private TurmaService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    public List<Turma> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    public Optional<Turma> getById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    public Turma create(@RequestBody Turma turma) { return service.save(turma); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public Turma update(@PathVariable Long id, @RequestBody Turma turma) {
        turma.setId(id);
        return service.save(turma);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public void delete(@PathVariable Long id) { service.deleteById(id); }

    @GetMapping("/busca")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    public Page<Turma> buscaAvancada(@RequestParam(required = false) String nome,
                                     @RequestParam(required = false) Integer anoMin,
                                     @RequestParam(required = false) Integer anoMax,
                                     @RequestParam(required = false) Boolean ativo,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sortBy,
                                     @RequestParam(defaultValue = "asc") String direction) {
        return service.buscarAvancado(nome, anoMin, anoMax, ativo, page, size, sortBy, direction);
    }
}
