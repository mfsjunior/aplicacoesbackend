package com.exemplo.crud.service;

import com.exemplo.crud.Model.Professor;
import com.exemplo.crud.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
