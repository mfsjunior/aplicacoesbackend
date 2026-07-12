package com.exemplo.gatewayservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/gateway")
public class GatewayController {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/matriculas/**")
    public ResponseEntity<String> matriculasProxy(HttpServletRequest request) {
        String path = request.getRequestURI().replaceFirst("/gateway/matriculas", "");
        String url = "http://matricula-service:8081/api/matriculas" + path + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        return restTemplate.getForEntity(url, String.class);
    }

    @GetMapping("/pessoas/**")
    public ResponseEntity<String> pessoasProxy(HttpServletRequest request) {
        String path = request.getRequestURI().replaceFirst("/gateway/pessoas", "");
        String url = "http://pessoa-service:8082/api/pessoas" + path + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        return restTemplate.getForEntity(url, String.class);
    }

    @GetMapping("/cursos/**")
    public ResponseEntity<String> cursosProxy(HttpServletRequest request) {
        String path = request.getRequestURI().replaceFirst("/gateway/cursos", "");
        String url = "http://curso-service:8083/api/cursos" + path + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        return restTemplate.getForEntity(url, String.class);
    }
}
