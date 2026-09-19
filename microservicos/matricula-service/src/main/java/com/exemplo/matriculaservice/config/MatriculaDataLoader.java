package com.exemplo.matriculaservice.config;

import com.exemplo.matriculaservice.model.Matricula;
import com.exemplo.matriculaservice.repository.MatriculaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatriculaDataLoader {
    @Bean
    CommandLineRunner initMatricula(MatriculaRepository repository) {
        return args -> {
            try { repository.save(new Matricula(1L, 1L, "2024-01-01", true)); } catch (Exception e) {}
            try { repository.save(new Matricula(2L, 2L, "2024-01-02", true)); } catch (Exception e) {}
        };
    }
}



