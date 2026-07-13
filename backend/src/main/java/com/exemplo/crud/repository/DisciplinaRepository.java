package com.exemplo.crud.repository;

import com.exemplo.crud.Model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {}
