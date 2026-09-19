package com.exemplo.crud.config;

import com.exemplo.crud.Model.Avaliacao;
import com.exemplo.crud.repository.AvaliacaoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvaliacaoDataLoader {
    @Bean
    CommandLineRunner initAvaliacao(AvaliacaoRepository repository) {
        return args -> {
            repository.save(new Avaliacao(null, 1L, 1L, 8.5, "2024-01-10", true));
            repository.save(new Avaliacao(null, 2L, 2L, 7.0, "2024-01-11", true));
        };
    }
}
