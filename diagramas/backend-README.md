# Backend Spring Boot

## Roadmap em Stack (Evolução por Branch)

Neste projeto, cada branch representa um momento da evolução.
Cada etapa nova nasce da etapa anterior, preservando continuidade.

### Ordem recomendada

1. `branch-1-monolito-basico`
2. `branch-2-alteracao-banco-dados`
3. `branch-3-consultas-avancadas`
4. `branch-4-auth-jwt`
5. `branch-5-entidades-avancadas`
6. `branch-6-pessoa-curso-separados`
7. `branch-7-roles-professor-aluno`
8. `branch-8-microservicos`

Observacao: a etapa 6 (pessoa-curso-separados) foi absorvida nas evolucoes anteriores da stack.

## Como usar cada branch

### 1. branch-1-monolito-basico

Base do sistema em monolito com MongoDB Atlas.

```bash
git checkout branch-1-monolito-basico
cd backend
# Configure a variável de ambiente da URI do MongoDB Atlas
mvn spring-boot:run
```

### 2. branch-2-alteracao-banco-dados

Evolução da branch 1 com migração para banco relacional.

```bash
git checkout branch-2-alteracao-banco-dados
cd backend
mvn spring-boot:run
```

### 3 a 8. Próximas evoluções

As etapas seguintes seguem o mesmo padrão de stack:

```bash
git checkout branch-N-nome-da-etapa
cd backend
mvn spring-boot:run
```

### 8. branch-8-microservicos

Transição para arquitetura de microserviços com gateway e serviços de domínio.

```bash
git checkout branch-8-microservicos
docker compose -f docker-compose.microservicos.yml up --build
```

Gateway disponível em `http://localhost:8080/gateway`.

## Regra de criação de novas etapas

Sempre crie a próxima branch a partir da branch atual, por exemplo:

```bash
git checkout branch-1-monolito-basico
git checkout -b branch-2-alteracao-banco-dados
```

Depois, para avançar para a etapa 3:

```bash
git checkout branch-2-alteracao-banco-dados
git checkout -b branch-3-consultas-avancadas
```

---

Se a etapa for publicada no GitHub, envie com:

```bash
git push -u origin nome-da-branch
```