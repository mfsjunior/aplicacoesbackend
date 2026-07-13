package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Pessoa;
import com.exemplo.crud.service.PessoaService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
@CrossOrigin(origins = "*")
public class PessoaController {
    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    public List<Pessoa> listar() {
        return service.listarTodas();
    }

    @PostMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return service.salvar(pessoa);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public Pessoa atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return service.atualizar(id, pessoa);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/busca")
    @PreAuthorize("hasAnyRole('PROFESSOR', 'ALUNO')")
    public Page<Pessoa> buscaAvancada(@RequestParam(required = false) String nome,
                                      @RequestParam(required = false) String email,
                                      @RequestParam(required = false) Integer idadeMin,
                                      @RequestParam(required = false) Integer idadeMax,
                                      @RequestParam(required = false) Boolean ativo,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sortBy,
                                      @RequestParam(defaultValue = "asc") String direction) {
        return service.buscarAvancado(nome, email, idadeMin, idadeMax, ativo, page, size, sortBy, direction);
    }
}
