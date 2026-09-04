package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Avaliacao;
import com.exemplo.crud.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/busca")
    public Page<Avaliacao> buscaAvancada(@RequestParam(required = false) Long pessoaId,
                                         @RequestParam(required = false) Long disciplinaId,
                                         @RequestParam(required = false) Double notaMin,
                                         @RequestParam(required = false) Double notaMax,
                                         @RequestParam(required = false) String dataInicio,
                                         @RequestParam(required = false) String dataFim,
                                         @RequestParam(required = false) Boolean ativo,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String sortBy,
                                         @RequestParam(defaultValue = "asc") String direction) {
        return service.buscarAvancado(pessoaId, disciplinaId, notaMin, notaMax, dataInicio, dataFim, ativo, page, size, sortBy, direction);
    }
}
