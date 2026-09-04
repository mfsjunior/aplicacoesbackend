package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Avaliacao;
import com.exemplo.crud.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService service;

    @GetMapping
    public List<Avaliacao> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Optional<Avaliacao> getById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public Avaliacao create(@RequestBody Avaliacao avaliacao) { return service.save(avaliacao); }

    @PutMapping("/{id}")
    public Avaliacao update(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        avaliacao.setId(id);
        return service.save(avaliacao);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}
