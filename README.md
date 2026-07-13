# CRUD Fullstack - README Geral da Aplicacao

Este e o guia geral completo do projeto.
A ideia e simples: voce comeca no monolito basico e evolui o sistema branch por branch.
Cada branch representa um evento (uma etapa de evolucao) do mesmo codigo.

## 1. Linha do tempo da evolucao

1. `branch-1-monolito-basico`
2. `branch-2-alteracao-banco-dados`
3. `branch-3-consultas-avancadas`
4. `branch-4-auth-jwt`
5. `branch-5-entidades-avancadas`
6. `branch-6-pessoa-curso-separados` (absorvida; sem ganho adicional isolado)
7. `branch-7-roles-professor-aluno`
8. `branch-8-microservicos`

## 2. Pre-requisitos

- Java 17+
- Maven 3.9+
- Docker Desktop (etapa 8)
- Postman

## 3. Como testar por evento (branch a branch)

## Evento 1 - Monolito basico

Branch: `branch-1-monolito-basico`

Objetivo:
- Subir backend monolitico e validar CRUD base.

Execucao:

```bash
git checkout branch-1-monolito-basico
cd backend
mvn spring-boot:run
```

Teste minimo no Postman:
- Validar `GET` e `POST` de Pessoa e Curso.

## Evento 2 - Alteracao de banco de dados

Branch: `branch-2-alteracao-banco-dados`

Objetivo:
- Validar migracao para banco relacional e persistencia via JPA.

Execucao:

```bash
git checkout branch-2-alteracao-banco-dados
cd backend
mvn spring-boot:run
```

Teste minimo no Postman:
- Criar, listar, atualizar e excluir em pelo menos 2 entidades.

## Evento 3 - Consultas avancadas

Branch: `branch-3-consultas-avancadas`

Objetivo:
- Validar filtros, paginacao e ordenacao em todas as entidades.

Execucao:

```bash
git checkout branch-3-consultas-avancadas
cd backend
mvn spring-boot:run
```

Teste minimo no Postman:
- Chamar endpoint `/busca` de todas as entidades de dominio.

## Evento 4 - Auth JWT

Branch: `branch-4-auth-jwt`

Objetivo:
- Validar login JWT e protecao de rotas.

Execucao:

```bash
git checkout branch-4-auth-jwt
cd backend
mvn spring-boot:run
```

Teste minimo no Postman:
- Login em `/api/auth/login`
- Usar token em rotas protegidas

## Evento 5 - Entidades avancadas

Branch: `branch-5-entidades-avancadas`

Objetivo:
- Validar constraints e validacoes (`@Valid`) nas entidades.

Execucao:

```bash
git checkout branch-5-entidades-avancadas
cd backend
mvn spring-boot:run
```

Teste minimo no Postman:
- Enviar payload invalido e confirmar erro de validacao.

## Evento 6 - Pessoa/Curso separados

Branch: `branch-6-pessoa-curso-separados`

Observacao:
- Esta etapa foi absorvida pelas evolucoes anteriores no fluxo atual.
- Siga para o evento 7.

## Evento 7 - Roles Professor/Aluno

Branch: `branch-7-roles-professor-aluno`

Objetivo:
- Validar regras de autorizacao por papel.

Execucao:

```bash
git checkout branch-7-roles-professor-aluno
cd backend
mvn spring-boot:run
```

Teste minimo no Postman:
1. Login professor e aluno em `/api/auth/login`.
2. `GET /api/auth/me` com os dois tokens.
3. Com aluno, tentar `POST` e validar `403`.
4. Com professor, validar CRUD completo.

## Evento 8 - Microservicos

Branch: `branch-8-microservicos`

Objetivo:
- Validar arquitetura com gateway e servicos separados.

Execucao:

```bash
git checkout branch-8-microservicos
docker compose -f docker-compose.microservicos.yml up --build
```

Teste minimo no Postman:
- `GET http://localhost:8080/gateway/matriculas`
- `GET http://localhost:8081/api/matriculas`
- `GET http://localhost:8081/api/matriculas/{id}/detalhada`

## 4. Roteiro unico de validacao completa (sugestao de aula)

1. Executar evento 1.
2. Executar evento 3.
3. Executar evento 4.
4. Executar evento 5.
5. Executar evento 7 (foco principal de regras de acesso).
6. Executar evento 8 (foco em gateway + matricula-service).

## 5. Checklist de entrega do aluno

1. Collection do Postman exportada.
2. Environment do Postman exportado.
3. Evidencias de resposta dos endpoints obrigatorios.
4. Link da branch testada.
5. Breve resumo do que mudou de um evento para o outro.

---

Este README e geral, completo e orientado por eventos de branch.
Para detalhes tecnicos internos, consulte os documentos da pasta `diagramas`.
