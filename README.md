# CRUD Fullstack - Guia Geral de Testes (Postman)

Este documento e o passo a passo oficial para o aluno testar a aplicacao inteira.
A evolucao do projeto foi feita em stack, onde cada branch nasce da anterior.

## 1. Ordem das branches (evolucao)

1. `branch-1-monolito-basico`
2. `branch-2-alteracao-banco-dados`
3. `branch-3-consultas-avancadas`
4. `branch-4-auth-jwt`
5. `branch-5-entidades-avancadas`
6. `branch-6-pessoa-curso-separados` (absorvida nas anteriores)
7. `branch-7-roles-professor-aluno`
8. `branch-8-microservicos`

## 2. Pre-requisitos

- Java 17+
- Maven 3.9+
- Docker Desktop (para etapa de microservicos)
- Postman

## 3. Teste completo da aplicacao (branch 7 - monolito)

A branch 7 e a melhor para validar todas as entidades com autenticacao e regras de perfil.

### 3.1 Subir a aplicacao

```bash
git checkout branch-7-roles-professor-aluno
cd backend
mvn spring-boot:run
```

Backend sobe em `http://localhost:8080`.

### 3.2 Criar ambiente no Postman

Crie um Environment chamado `crud-local` com as variaveis:

- `baseUrl = http://localhost:8080`
- `tokenProfessor = `
- `tokenAluno = `

### 3.3 Autenticacao (ordem recomendada)

#### A) Login como professor (usuario seed)

Request:

- Metodo: `POST`
- URL: `{{baseUrl}}/api/auth/login`
- Body JSON:

```json
{
  "username": "professor",
  "password": "prof123"
}
```

Copie o `token` da resposta para `tokenProfessor`.

#### B) Login como aluno (usuario seed)

Request:

- Metodo: `POST`
- URL: `{{baseUrl}}/api/auth/login`
- Body JSON:

```json
{
  "username": "aluno",
  "password": "aluno123"
}
```

Copie o `token` da resposta para `tokenAluno`.

#### C) Cadastro publico de aluno

Request:

- Metodo: `POST`
- URL: `{{baseUrl}}/api/auth/register-aluno`
- Body JSON:

```json
{
  "username": "aluno_teste",
  "password": "aluno123"
}
```

### 3.4 Headers padrao por perfil

Para requests protegidos:

- Professor: `Authorization: Bearer {{tokenProfessor}}`
- Aluno: `Authorization: Bearer {{tokenAluno}}`

## 4. Matriz de testes por entidade (branch 7)

Regra geral de autorizacao:

- `GET`: ALUNO e PROFESSOR
- `POST`, `PUT`, `DELETE`: somente PROFESSOR

### 4.1 Pessoa

Base: `{{baseUrl}}/api/pessoas`

Payload exemplo:

```json
{
  "nome": "Ana Souza",
  "idade": 21,
  "email": "ana.souza@example.com",
  "ativo": true
}
```

Testes:

1. `POST /api/pessoas` (professor)
2. `GET /api/pessoas` (professor e aluno)
3. `PUT /api/pessoas/{id}` (professor)
4. `GET /api/pessoas/busca?nome=ana&page=0&size=10&sortBy=nome&direction=asc`
5. `DELETE /api/pessoas/{id}` (professor)

### 4.2 Curso

Base: `{{baseUrl}}/api/curso`

Payload exemplo:

```json
{
  "nome": "Engenharia de Software",
  "cargaHoraria": 3200,
  "ativo": true
}
```

Testes:

1. `POST /api/curso`
2. `GET /api/curso`
3. `PUT /api/curso/{id}`
4. `GET /api/curso/busca?nome=engenharia&cargaHorariaMin=1000`
5. `DELETE /api/curso/{id}`

### 4.3 Professor

Base: `{{baseUrl}}/api/professores`

Payload exemplo:

```json
{
  "nome": "Carlos Lima",
  "idade": 40,
  "email": "carlos.lima@example.com",
  "area": "Matematica",
  "ativo": true
}
```

Testes:

1. `POST /api/professores`
2. `GET /api/professores`
3. `PUT /api/professores/{id}`
4. `GET /api/professores/busca?area=matematica&ativo=true`
5. `DELETE /api/professores/{id}`

### 4.4 Disciplina

Base: `{{baseUrl}}/api/disciplinas`

Payload exemplo:

```json
{
  "nome": "Calculo I",
  "ativo": true
}
```

Testes:

1. `POST /api/disciplinas`
2. `GET /api/disciplinas`
3. `PUT /api/disciplinas/{id}`
4. `GET /api/disciplinas/busca?nome=calculo`
5. `DELETE /api/disciplinas/{id}`

### 4.5 Turma

Base: `{{baseUrl}}/api/turmas`

Payload exemplo:

```json
{
  "nome": "Turma A",
  "ano": 2026,
  "ativo": true
}
```

Testes:

1. `POST /api/turmas`
2. `GET /api/turmas`
3. `PUT /api/turmas/{id}`
4. `GET /api/turmas/busca?anoMin=2025&anoMax=2026`
5. `DELETE /api/turmas/{id}`

### 4.6 Matricula

Base: `{{baseUrl}}/api/matriculas`

Payload exemplo:

```json
{
  "pessoaId": 1,
  "cursoId": 1,
  "dataMatricula": "2026-07-12",
  "ativo": true
}
```

Testes:

1. `POST /api/matriculas`
2. `GET /api/matriculas`
3. `PUT /api/matriculas/{id}`
4. `GET /api/matriculas/busca?pessoaId=1&cursoId=1`
5. `DELETE /api/matriculas/{id}`

### 4.7 Avaliacao

Base: `{{baseUrl}}/api/avaliacoes`

Payload exemplo:

```json
{
  "pessoaId": 1,
  "disciplinaId": 1,
  "nota": 8.5,
  "data": "2026-07-12",
  "ativo": true
}
```

Testes:

1. `POST /api/avaliacoes`
2. `GET /api/avaliacoes`
3. `PUT /api/avaliacoes/{id}`
4. `GET /api/avaliacoes/busca?notaMin=7&notaMax=10`
5. `DELETE /api/avaliacoes/{id}`

## 5. Testes de perfil (obrigatorio)

### 5.1 Endpoint de perfil logado

- `GET {{baseUrl}}/api/auth/me`
- Testar com `tokenProfessor` e `tokenAluno`.

### 5.2 Listagem de usuarios (somente professor)

- `GET {{baseUrl}}/api/auth/usuarios`
- Com `tokenProfessor`: deve retornar lista.
- Com `tokenAluno`: deve retornar `403`.

### 5.3 Verificacao de autorizacao por entidade

Tente executar `POST` em qualquer entidade com `tokenAluno`.
Esperado: `403 Forbidden`.

## 6. Teste da etapa microservicos (branch 8)

A etapa 8 adiciona gateway e servicos separados.

### 6.1 Subir stack de microservicos

```bash
git checkout branch-8-microservicos
docker compose -f docker-compose.microservicos.yml up --build
```

### 6.2 Endpoints principais para validar no Postman

Gateway:

- `GET http://localhost:8080/gateway/matriculas`

Servico de matricula direto:

- `GET http://localhost:8081/api/matriculas`
- `POST http://localhost:8081/api/matriculas`
- `GET http://localhost:8081/api/matriculas/{id}/detalhada`

Observacao importante:

- Na etapa 8, o servico de matricula esta mais completo que os demais, e deve ser o foco da validacao funcional entre servicos.

## 7. Checklist final do aluno

1. Conseguiu autenticar com professor e aluno.
2. Validou regra de permissao (ALUNO nao cria/edita/exclui).
3. Executou CRUD basico de todas as entidades na branch 7.
4. Executou consultas avancadas (`/busca`) em todas as entidades.
5. Validou endpoint `/api/auth/me`.
6. Validou endpoint `/api/auth/usuarios` com controle de acesso.
7. Subiu branch 8 e testou gateway + matricula-service.

Se todos os itens acima passaram, a aplicacao foi testada de ponta a ponta no escopo do projeto.
