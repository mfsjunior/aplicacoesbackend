package com.exemplo.matriculaservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "pessoa-service", url = "${pessoa-service.url:http://localhost:8082}")
public interface PessoaClient {

    @GetMapping("/api/pessoas/{id}")
    PessoaDTO getPessoaById(@PathVariable("id") Long id, @RequestHeader(value = "Authorization", required = false) String token);
}
