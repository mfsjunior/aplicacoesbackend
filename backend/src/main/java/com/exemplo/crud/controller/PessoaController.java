package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Pessoa;
import com.exemplo.crud.service.PessoaService;
import org.springframework.data.domain.Page;
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
    public List<Pessoa> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return service.salvar(pessoa);
    }

    @PutMapping("/{id}")
    public Pessoa atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return service.atualizar(id, pessoa);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/busca")
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
