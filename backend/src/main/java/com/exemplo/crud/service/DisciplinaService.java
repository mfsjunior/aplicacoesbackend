package com.exemplo.crud.service;

import com.exemplo.crud.Model.Disciplina;
import com.exemplo.crud.repository.DisciplinaRepository;
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
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository repository;

    public List<Disciplina> findAll() { return repository.findAll(); }
    public Optional<Disciplina> findById(Long id) { return repository.findById(id); }
    public Disciplina save(Disciplina disciplina) { return repository.save(disciplina); }
    public void deleteById(Long id) { repository.deleteById(id); }

    public Page<Disciplina> buscarAvancado(String nome,
                                           Boolean ativo,
                                           int page,
                                           int size,
                                           String sortBy,
                                           String direction) {
        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Disciplina> spec = Specification.where(null);

        if (nome != null && !nome.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
        }
        if (ativo != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("ativo"), ativo));
        }

        return repository.findAll(spec, pageable);
    }
}
