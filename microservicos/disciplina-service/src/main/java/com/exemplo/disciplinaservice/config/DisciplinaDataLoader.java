package com.exemplo.disciplinaservice.config;

import com.exemplo.disciplinaservice.model.Disciplina;
import com.exemplo.disciplinaservice.repository.DisciplinaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DisciplinaDataLoader {
    @Bean
    CommandLineRunner initDisciplina(DisciplinaRepository repository) {
        return args -> {
            try { repository.save(new Disciplina(null, "Matemática", true)); } catch (Exception e) {}
            try { repository.save(new Disciplina(null, "História", true)); } catch (Exception e) {}
        };
    }
}



