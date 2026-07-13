package com.exemplo.crud.service;

import com.exemplo.crud.Model.Avaliacao;
import com.exemplo.crud.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
