package com.exemplo.crud.service;

import com.exemplo.crud.Model.Professor;
import com.exemplo.crud.repository.ProfessorRepository;
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
public class ProfessorService {
    @Autowired
    private ProfessorRepository repository;

    public List<Professor> findAll() { return repository.findAll(); }
    public Optional<Professor> findById(Long id) { return repository.findById(id); }
    public Professor save(Professor professor) { return repository.save(professor); }
    public void deleteById(Long id) { repository.deleteById(id); }

    public Page<Professor> buscarAvancado(String nome,
                                          String email,
                                          String area,
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

        Specification<Professor> spec = Specification.where(null);

        if (nome != null && !nome.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
        }
        if (email != null && !email.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
        }
        if (area != null && !area.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("area")), "%" + area.toLowerCase() + "%"));
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
