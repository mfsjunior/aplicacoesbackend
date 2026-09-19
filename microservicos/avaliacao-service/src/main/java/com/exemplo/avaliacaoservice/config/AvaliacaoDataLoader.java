package com.exemplo.avaliacaoservice.config;

import com.exemplo.avaliacaoservice.model.Avaliacao;
import com.exemplo.avaliacaoservice.repository.AvaliacaoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvaliacaoDataLoader {
    @Bean
    CommandLineRunner initAvaliacao(AvaliacaoRepository repository) {
        return args -> {
            try { repository.save(new Avaliacao(null, 1L, 1L, 8.5, "2024-01-10", true)); } catch (Exception e) {}
            try { repository.save(new Avaliacao(null, 2L, 2L, 7.0, "2024-01-11", true)); } catch (Exception e) {}
        };
    }
}



