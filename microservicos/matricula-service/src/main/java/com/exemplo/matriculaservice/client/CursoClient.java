package com.exemplo.matriculaservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "curso-service", url = "${curso-service.url:http://localhost:8083}")
public interface CursoClient {

    @GetMapping("/api/cursos/{id}")
    CursoDTO getCursoById(@PathVariable("id") Long id, @RequestHeader(value = "Authorization", required = false) String token);
}
