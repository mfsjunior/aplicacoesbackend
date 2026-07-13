package com.exemplo.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.exemplo.crud.Model.Pessoa;
import com.exemplo.crud.repository.PessoaRepository;

@Service
public class PessoaService {
    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public List<Pessoa> listarTodas() {
        return repository.findAll();
    }

    public Pessoa salvar(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public Pessoa atualizar(@PathVariable Long id, Pessoa novaPessoa) {
        return repository.findById(id).map(p -> {
            p.setNome(novaPessoa.getNome());
            p.setIdade(novaPessoa.getIdade());
            p.setEmail(novaPessoa.getEmail());
            p.setAtivo(novaPessoa.isAtivo());
            return repository.save(p);
        }).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Page<Pessoa> buscarAvancado(String nome,
                                       String email,
                                       Integer idadeMin,
                                       Integer idadeMax,
                                       Boolean ativo,
                                       int page,
                                       int size,
                                       String sortBy,
                                       String direction) {
        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Pessoa> spec = Specification.where(null);

        if (nome != null && !nome.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
        }
        if (email != null && !email.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
        }
        if (idadeMin != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("idade"), idadeMin));
        }
        if (idadeMax != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("idade"), idadeMax));
        }
        if (ativo != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("ativo"), ativo));
        }

        return repository.findAll(spec, pageable);
    }
}
