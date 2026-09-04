package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Matricula;
import com.exemplo.crud.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    @Autowired
    private MatriculaService service;

    @GetMapping
    public List<Matricula> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Optional<Matricula> getById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public Matricula create(@RequestBody Matricula matricula) { return service.save(matricula); }

    @PutMapping("/{id}")
    public Matricula update(@PathVariable Long id, @RequestBody Matricula matricula) {
        matricula.setId(id);
        return service.save(matricula);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}
