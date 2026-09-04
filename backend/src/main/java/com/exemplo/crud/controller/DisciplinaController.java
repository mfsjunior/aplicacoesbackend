package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Disciplina;
import com.exemplo.crud.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {
    @Autowired
    private DisciplinaService service;

    @GetMapping
    public List<Disciplina> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Optional<Disciplina> getById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public Disciplina create(@RequestBody Disciplina disciplina) { return service.save(disciplina); }

    @PutMapping("/{id}")
    public Disciplina update(@PathVariable Long id, @RequestBody Disciplina disciplina) {
        disciplina.setId(id);
        return service.save(disciplina);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}
