package com.exemplo.crud.service;

import com.exemplo.crud.Model.Matricula;
import com.exemplo.crud.repository.MatriculaRepository;
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
public class MatriculaService {
    @Autowired
    private MatriculaRepository repository;

    public List<Matricula> findAll() { return repository.findAll(); }
    public Optional<Matricula> findById(Long id) { return repository.findById(id); }
    public Matricula save(Matricula matricula) { return repository.save(matricula); }
    public void deleteById(Long id) { repository.deleteById(id); }

    public Page<Matricula> buscarAvancado(Long pessoaId,
                                          Long cursoId,
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

        Specification<Matricula> spec = Specification.where(null);

        if (pessoaId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("pessoaId"), pessoaId));
        }
        if (cursoId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("cursoId"), cursoId));
        }
        if (dataInicio != null && !dataInicio.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("dataMatricula"), dataInicio));
        }
        if (dataFim != null && !dataFim.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("dataMatricula"), dataFim));
        }
        if (ativo != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("ativo"), ativo));
        }

        return repository.findAll(spec, pageable);
    }
}
