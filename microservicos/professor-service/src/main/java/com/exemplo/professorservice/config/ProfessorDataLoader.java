package com.exemplo.professorservice.config;

import com.exemplo.professorservice.model.Professor;
import com.exemplo.professorservice.repository.ProfessorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfessorDataLoader {
    @Bean
    CommandLineRunner initProfessor(ProfessorRepository repository) {
        return args -> {
            repository.save(new Professor(null, "João Silva", "Matemática", true));
            repository.save(new Professor(null, "Maria Souza", "História", true));
        };
    }
}

