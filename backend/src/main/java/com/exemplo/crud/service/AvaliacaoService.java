package com.exemplo.crud.service;

import com.exemplo.crud.Model.Avaliacao;
import com.exemplo.crud.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository repository;

    public List<Avaliacao> findAll() { return repository.findAll(); }
    public Optional<Avaliacao> findById(Long id) { return repository.findById(id); }
    public Avaliacao save(Avaliacao avaliacao) { return repository.save(avaliacao); }
    public void deleteById(Long id) { repository.deleteById(id); }

    public Page<Avaliacao> buscarAvancado(Long pessoaId,
                                          Long disciplinaId,
                                          Double notaMin,
                                          Double notaMax,
                                          String dataInicio,
                                          String dataFim,
                                          Boolean ativo,
                                          int page,
                                          int size,
                                          String sortBy,
                                          String direction) {
        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Avaliacao> spec = Specification.where(null);

        if (pessoaId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("pessoaId"), pessoaId));
        }
        if (disciplinaId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("disciplinaId"), disciplinaId));
        }
        if (notaMin != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("nota"), notaMin));
        }
        if (notaMax != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("nota"), notaMax));
        }
        if (dataInicio != null && !dataInicio.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("data"), dataInicio));
        }
        if (dataFim != null && !dataFim.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("data"), dataFim));
        }
        if (ativo != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("ativo"), ativo));
        }

        return repository.findAll(spec, pageable);
    }
}
