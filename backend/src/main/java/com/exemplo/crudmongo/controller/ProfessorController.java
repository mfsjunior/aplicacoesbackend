package com.exemplo.crudmongo.controller;

import com.exemplo.crudmongo.Model.Professor;
import com.exemplo.crudmongo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/professores", "/professores"})
@CrossOrigin(origins = "*")
public class ProfessorController {
    @Autowired
    private ProfessorService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    public List<Professor> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    public Optional<Professor> getById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    public Professor create(@RequestBody Professor professor) { return service.save(professor); }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public Professor update(@PathVariable Long id, @RequestBody Professor professor) {
        professor.setId(id);
        return service.save(professor);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public void delete(@PathVariable Long id) { service.deleteById(id); }

    @GetMapping("/busca")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    public Page<Professor> buscaAvancada(@RequestParam(required = false) String nome,
                                         @RequestParam(required = false) String email,
                                         @RequestParam(required = false) String area,
                                         @RequestParam(required = false) Integer idadeMin,
                                         @RequestParam(required = false) Integer idadeMax,
                                         @RequestParam(required = false) Boolean ativo,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String sortBy,
                                         @RequestParam(defaultValue = "asc") String direction) {
        return service.buscarAvancado(nome, email, area, idadeMin, idadeMax, ativo, page, size, sortBy, direction);
    }
}