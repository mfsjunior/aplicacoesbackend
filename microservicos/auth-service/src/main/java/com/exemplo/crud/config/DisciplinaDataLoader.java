package com.exemplo.crud.config;

import com.exemplo.crud.Model.Disciplina;
import com.exemplo.crud.repository.DisciplinaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DisciplinaDataLoader {
    @Bean
    CommandLineRunner initDisciplina(DisciplinaRepository repository) {
        return args -> {
            repository.save(new Disciplina(null, "Matemática", true));
            repository.save(new Disciplina(null, "História", true));
        };
    }
}
