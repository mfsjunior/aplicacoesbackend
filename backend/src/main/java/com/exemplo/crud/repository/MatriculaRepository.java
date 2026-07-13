package com.exemplo.crud.repository;

import com.exemplo.crud.Model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {}
