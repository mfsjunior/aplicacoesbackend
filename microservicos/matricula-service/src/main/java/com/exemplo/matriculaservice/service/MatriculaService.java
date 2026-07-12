package com.exemplo.matriculaservice.service;
import com.exemplo.matriculaservice.dto.MatriculaDetalhadaDTO;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;


import com.exemplo.matriculaservice.model.Matricula;
import com.exemplo.matriculaservice.repository.MatriculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada de negócio do microserviço de Matrículas.
 * Toda a lógica de negócio fica aqui ? o controller só delega.
 */
@Service
public class MatriculaService {
    private final RestTemplate restTemplate = new RestTemplate();


    private final MatriculaRepository repository;

    public MatriculaService(MatriculaRepository repository) {
        this.repository = repository;
    }

    /** Lista todas as matrículas */
    public List<Matricula> listarTodas() {
        return repository.findAll();
    }

    /** Busca uma matrícula pelo ID */
    public Optional<Matricula> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /** Busca matrícula detalhada (com nome da pessoa e do curso via HTTP) */
    public Optional<MatriculaDetalhadaDTO> buscarDetalhadaPorId(Long id) {
        return repository.findById(id).map(matricula -> {
            String nomePessoa = "indisponível";
            String nomeCurso = "indisponível";
            // Busca nome da pessoa
            try {
                String urlPessoa = "http://localhost:8082/api/pessoas/" + matricula.getPessoaId();
                PessoaDTO pessoa = restTemplate.getForObject(urlPessoa, PessoaDTO.class);
                if (pessoa != null && pessoa.getNome() != null) nomePessoa = pessoa.getNome();
            } catch (RestClientException e) {
                nomePessoa = "indisponível";
            }
            // Busca nome do curso
            try {
                String urlCurso = "http://localhost:8083/api/cursos/" + matricula.getCursoId();
                CursoDTO curso = restTemplate.getForObject(urlCurso, CursoDTO.class);
                if (curso != null && curso.getNome() != null) nomeCurso = curso.getNome();
            } catch (RestClientException e) {
                nomeCurso = "indisponível";
            }
            return new MatriculaDetalhadaDTO(
                matricula.getId(),
                matricula.getPessoaId(),
                nomePessoa,
                matricula.getCursoId(),
                nomeCurso,
                matricula.getDataMatricula(),
                matricula.isAtivo()
            );
        });
    }

    // DTOs internos para deserialização
    private static class PessoaDTO {
        private String nome;
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
    }
    private static class CursoDTO {
        private String nome;
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
    }

    /** Lista matrículas de uma pessoa específica */
    public List<Matricula> listarPorPessoa(Long pessoaId) {
        return repository.findByPessoaId(pessoaId);
    }

    /** Lista matrículas de um curso específico */
    public List<Matricula> listarPorCurso(Long cursoId) {
        return repository.findByCursoId(cursoId);
    }

    /** Cria uma nova matrícula */
    public Matricula salvar(Matricula matricula) {
        return repository.save(matricula);
    }

    /** Atualiza uma matrícula existente */
    public Matricula atualizar(Long id, Matricula dados) {
        Matricula existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada: " + id));
        existente.setPessoaId(dados.getPessoaId());
        existente.setCursoId(dados.getCursoId());
        existente.setDataMatricula(dados.getDataMatricula());
        existente.setAtivo(dados.isAtivo());
        return repository.save(existente);
    }

    /** Desativa uma matrícula (soft delete) */
    public void desativar(Long id) {
        Matricula existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada: " + id));
        existente.setAtivo(false);
        repository.save(existente);
    }

    /** Remove permanentemente uma matrícula */
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
