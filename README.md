# CRUD Fullstack - README Geral (Engenharia Reversa por Branch)

Este documento descreve tecnicamente o que acontece em cada branch da evolucao.
A leitura correta e sequencial: voce comeca no monolito basico e avanca evento por evento.

## 1. Regra do projeto

Cada branch representa um evento de evolucao.
A branch seguinte sempre herda a anterior e adiciona mudancas novas.

Sequencia usada:

1. `branch-1-monolito-basico`
2. `branch-2-alteracao-banco-dados`
3. `branch-3-consultas-avancadas`
4. `branch-4-auth-jwt`
5. `branch-5-entidades-avancadas`
6. `branch-6-pessoa-curso-separados` (na pratica foi absorvida pelas etapas anteriores)
7. `branch-7-roles-professor-aluno`
8. `branch-8-microservicos`

## 2. Mapa tecnico do backend

Pacote base atual:

- `com.exemplo.crud`

Camadas do monolito:

- `Model`
- `repository`
- `service`
- `controller`
- `config`
- `dto`

Entidades principais:

- Pessoa
- Curso
- Professor
- Disciplina
- Turma
- Matricula
- Avaliacao
- Usuario

## 3. O que cada branch adiciona (reversa tecnica)

## Branch 1 - Monolito basico

Base tecnica observada:

- Aplicacao Spring Boot monolitica em `backend`.
- Persistencia via Spring Data JPA e banco H2 em memoria.
- Estrutura em camadas completa para entidades do dominio.
- Endpoints REST de CRUD ja presentes para as entidades principais.

Execucao:

```bash
git checkout branch-1-monolito-basico
cd backend
mvn spring-boot:run
```

## Branch 2 - Alteracao de banco de dados

Evento esperado:

- Consolidacao do backend com banco relacional e configuracoes de persistencia.

Indicador tecnico:

- Mantem base JPA/H2 consolidada e estrutura para evolucoes seguintes.

Execucao:

```bash
git checkout branch-2-alteracao-banco-dados
cd backend
mvn spring-boot:run
```

## Branch 3 - Consultas avancadas

Mudanca tecnica relevante:

- Repositorios passam a usar `JpaSpecificationExecutor`.
- Services ganham busca dinamica com `Specification`.
- Controllers expõem rotas `/busca` com filtros opcionais.
- Suporte a paginacao e ordenacao em todas as entidades de dominio.

Arquivos/areas chave:

- `repository/*Repository.java`
- `service/*Service.java`
- `controller/*Controller.java`

Execucao:

```bash
git checkout branch-3-consultas-avancadas
cd backend
mvn spring-boot:run
```

## Branch 4 - Auth JWT

Mudanca tecnica relevante:

- Padronizacao das rotas de dominio com base `/api`.
- Homogeneizacao de acesso com regras de seguranca por metodo HTTP.
- Integracao consistente com filtro JWT ja existente.

Arquivos/areas chave:

- `config/SecurityConfig.java`
- `controller/*Controller.java`

Execucao:

```bash
git checkout branch-4-auth-jwt
cd backend
mvn spring-boot:run
```

## Branch 5 - Entidades avancadas

Mudanca tecnica relevante:

- Entidades recebem constraints de coluna e validacoes Bean Validation.
- Controllers passam a validar payload com `@Valid` nos endpoints de escrita.
- Regras de dominio ficam mais estritas no modelo (tamanho, formato, faixa).

Arquivos/areas chave:

- `Model/*.java`
- `controller/*Controller.java`

Execucao:

```bash
git checkout branch-5-entidades-avancadas
cd backend
mvn spring-boot:run
```

## Branch 6 - Pessoa/Curso separados

Observacao tecnica:

- No fluxo real desta stack, essa etapa nao introduziu ganho isolado adicional.
- Separacao funcional de Pessoa/Curso ja estava contemplada nas etapas anteriores.

## Branch 7 - Roles Professor/Aluno

Mudanca tecnica relevante:

- Evolucao da camada de autenticacao/autorizacao por perfil.
- Novos endpoints em auth para operacao por papel.
- Cadastro publico de aluno e endpoint de perfil do usuario autenticado.
- Listagem de usuarios resumida restrita a professor.

Arquivos/areas chave:

- `controller/AuthController.java`
- `service/UsuarioService.java`
- `repository/UsuarioRepository.java`
- `dto/UsuarioResumoDTO.java`
- `config/SecurityConfig.java`

Execucao:

```bash
git checkout branch-7-roles-professor-aluno
cd backend
mvn spring-boot:run
```

## Branch 8 - Microservicos

Mudanca tecnica relevante:

- Introducao da estrutura `microservicos/*`.
- Introducao de `gateway-service` para roteamento HTTP.
- Compose dedicado para subir os servicos em conjunto.
- `matricula-service` e o modulo mais completo na etapa de microservicos.

Arquivos/areas chave:

- `gateway-service/*`
- `microservicos/*`
- `docker-compose.microservicos.yml`

Execucao:

```bash
git checkout branch-8-microservicos
docker compose -f docker-compose.microservicos.yml up --build
```

## 4. Teste geral no Postman (resumo objetivo)

## Monolito (branch 7)

1. Login professor: `POST /api/auth/login`.
2. Login aluno: `POST /api/auth/login`.
3. Perfil logado: `GET /api/auth/me`.
4. CRUD completo de todas as entidades com professor.
5. Validar `403` ao tentar escrita com aluno.
6. Executar `/busca` de todas as entidades.

## Microservicos (branch 8)

1. `GET http://localhost:8080/gateway/matriculas`.
2. `GET http://localhost:8081/api/matriculas`.
3. `GET http://localhost:8081/api/matriculas/{id}/detalhada`.

## 5. Entrega recomendada do aluno

1. Collection do Postman exportada.
2. Environment do Postman exportado.
3. Evidencias de resposta dos endpoints obrigatorios.
4. Link da branch validada.
5. Resumo do que mudou em cada evento.
