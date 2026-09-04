package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Turma;
import com.exemplo.crud.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    @Autowired
    private TurmaService service;

    @GetMapping
    public List<Turma> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Optional<Turma> getById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public Turma create(@RequestBody Turma turma) { return service.save(turma); }

    @PutMapping("/{id}")
    public Turma update(@PathVariable Long id, @RequestBody Turma turma) {
        turma.setId(id);
        return service.save(turma);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.deleteById(id); }

    @GetMapping("/busca")
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
