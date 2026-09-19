package com.exemplo.turmaservice.config;

import com.exemplo.turmaservice.model.Turma;
import com.exemplo.turmaservice.repository.TurmaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TurmaDataLoader {
    @Bean
    CommandLineRunner initTurma(TurmaRepository repository) {
        return args -> {
            repository.save(new Turma(null, "Turma A", 2024, true));
            repository.save(new Turma(null, "Turma B", 2024, true));
        };
    }
}

