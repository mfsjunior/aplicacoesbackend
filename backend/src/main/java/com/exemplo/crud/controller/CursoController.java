package com.exemplo.crud.controller;

import com.exemplo.crud.Model.Curso;
import com.exemplo.crud.service.CursoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
@CrossOrigin(origins = "*")
public class CursoController {
    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Curso> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public Curso criar(@RequestBody Curso curso) {
        return service.salvar(curso);
    }

    @PutMapping("/{id}")
    public Curso atualizar(@PathVariable Long id, @RequestBody Curso curso) {
        return service.atualizar(id, curso);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/busca")
    public Page<Curso> buscaAvancada(@RequestParam(required = false) String nome,
                                     @RequestParam(required = false) Integer cargaHorariaMin,
                                     @RequestParam(required = false) Integer cargaHorariaMax,
                                     @RequestParam(required = false) Boolean ativo,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sortBy,
                                     @RequestParam(defaultValue = "asc") String direction) {
        return service.buscarAvancado(nome, cargaHorariaMin, cargaHorariaMax, ativo, page, size, sortBy, direction);
    }
}
