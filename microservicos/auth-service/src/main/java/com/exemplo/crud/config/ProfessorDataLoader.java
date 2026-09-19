package com.exemplo.crud.config;

import com.exemplo.crud.Model.Professor;
import com.exemplo.crud.repository.ProfessorRepository;
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
