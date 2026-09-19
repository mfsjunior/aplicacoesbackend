package com.exemplo.matriculaservice.service;

import com.exemplo.matriculaservice.client.CursoClient;
import com.exemplo.matriculaservice.client.CursoDTO;
import com.exemplo.matriculaservice.client.PessoaClient;
import com.exemplo.matriculaservice.client.PessoaDTO;
import com.exemplo.matriculaservice.dto.MatriculaDetalhadaDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.exemplo.matriculaservice.model.Matricula;
import com.exemplo.matriculaservice.repository.MatriculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    private final PessoaClient pessoaClient;
    private final CursoClient cursoClient;
    private final MatriculaRepository repository;

    public MatriculaService(MatriculaRepository repository, PessoaClient pessoaClient, CursoClient cursoClient) {
        this.repository = repository;
        this.pessoaClient = pessoaClient;
        this.cursoClient = cursoClient;
    }

    public List<Matricula> listarTodas() {
        return repository.findAll();
    }

    public Optional<Matricula> buscarPorId(Long id) {
        return repository.findById(id);
    }

    private String getBearerToken() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return request.getHeader("Authorization");
        } catch (Exception e) {
            return null;
        }
    }

    public Optional<MatriculaDetalhadaDTO> buscarDetalhadaPorId(Long id) {
        return repository.findById(id).map(matricula -> {
            String token = getBearerToken();
            String nomePessoa = buscarNomePessoaComResiliencia(matricula.getPessoaId(), token);
            String nomeCurso = buscarNomeCursoComResiliencia(matricula.getCursoId(), token);
            
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

    @CircuitBreaker(name = "pessoaService", fallbackMethod = "pessoaFallback")
    public String buscarNomePessoaComResiliencia(Long pessoaId, String token) {
        PessoaDTO pessoa = pessoaClient.getPessoaById(pessoaId, token);
        return (pessoa != null && pessoa.getNome() != null) ? pessoa.getNome() : "indisponivel";
    }

    public String pessoaFallback(Long pessoaId, String token, Throwable t) {
        return "indisponivel (fallback)";
    }

    @CircuitBreaker(name = "cursoService", fallbackMethod = "cursoFallback")
    public String buscarNomeCursoComResiliencia(Long cursoId, String token) {
        CursoDTO curso = cursoClient.getCursoById(cursoId, token);
        return (curso != null && curso.getNome() != null) ? curso.getNome() : "indisponivel";
    }

    public String cursoFallback(Long cursoId, String token, Throwable t) {
        return "indisponivel (fallback)";
    }

    public List<Matricula> listarPorPessoa(Long pessoaId) {
        return repository.findByPessoaId(pessoaId);
    }

    public List<Matricula> listarPorCurso(Long cursoId) {
        return repository.findByCursoId(cursoId);
    }

    public Matricula salvar(Matricula matricula) {
        return repository.save(matricula);
    }

    public Matricula atualizar(Long id, Matricula dados) {
        Matricula existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matricula nao encontrada: " + id));
        existente.setPessoaId(dados.getPessoaId());
        existente.setCursoId(dados.getCursoId());
        existente.setDataMatricula(dados.getDataMatricula());
        existente.setAtivo(dados.isAtivo());
        return repository.save(existente);
    }

    public void desativar(Long id) {
        Matricula existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matricula nao encontrada: " + id));
        existente.setAtivo(false);
        repository.save(existente);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
