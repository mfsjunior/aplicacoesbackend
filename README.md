# CRUD Fullstack - README 
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

## Matriz unica de validacao por branch

| Branch | Objetivo | Endpoints obrigatorios | Evidencia esperada |
|---|---|---|---|
| `branch-1-monolito-basico` | Subir monolito com MongoDB e validar CRUD inicial. | `GET /pessoas`, `POST /pessoas`, `GET /curso`, `POST /curso` | JSON de leitura e criacao funcionando com status 200/201. |
| `branch-2-alteracao-banco-dados` | Consolidar persistencia relacional e rotas base `/api`. | `GET /api/pessoas`, `POST /api/pessoas`, `GET /api/curso` | Respostas com dados persistidos apos reinicio da aplicacao. |
| `branch-3-consultas-avancadas` | Validar filtros, paginacao e ordenacao nas buscas. | `GET /api/pessoas/busca?...`, `GET /api/curso/busca?...`, `GET /api/avaliacao/busca?...` | Resultado paginado com metadados (`page`, `size`, ordenacao). |
| `branch-4-auth-jwt` | Garantir autenticacao JWT e acesso protegido. | `POST /api/auth/login`, `GET /api/pessoas` com token | Token retornado no login e acesso autorizado com Bearer. |
| `branch-5-entidades-avancadas` | Validar regras de dominio e Bean Validation. | `POST /api/pessoas` invalido, `POST /api/professor` invalido | Erros de validacao com status 400 e mensagens de campo. |
| `branch-6-pessoa-curso-separados` | Revalidar estabilidade da etapa intermediaria. | Repetir `CRUD` e `/busca` de Pessoa e Curso | Mesmo comportamento funcional das etapas 3 e 5. |
| `branch-7-roles-professor-aluno` | Confirmar autorizacao por perfil (ALUNO x PROFESSOR). | `POST /api/auth/register-aluno`, `GET /api/auth/usuarios`, `POST /api/pessoas` com token ALUNO | `GET /usuarios` permitido para professor e `403` para operacao de escrita com aluno. |
| `branch-8-microservicos` | Validar gateway + servicos de matricula em arquitetura distribuida. | `GET /gateway/matriculas`, `POST /gateway/matriculas`, `GET /api/matriculas/{id}/detalhada` | Resposta via gateway e resposta detalhada do servico de matricula. |

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
- Persistencia via Spring Data MongoDB (sem JPA).
- Conexao direcionada para MongoDB (local ou MongoDB Atlas).
- Estrutura em camadas completa para entidades do dominio.
- Endpoints REST de CRUD ja presentes para as entidades principais.

Execucao:

Antes de executar, crie uma conta no MongoDB Atlas, crie um cluster e copie a URI de conexao (Connect > Drivers) para usar na variavel `MONGODB_URI`.

```bash
git checkout branch-1-monolito-basico
cd backend
$env:MONGODB_URI="mongodb+srv://<usuario>:<senha>@<cluster>/<database>?retryWrites=true&w=majority"
mvn spring-boot:run
```

Endpoints de teste (branch 1):

1. `GET /pessoas`
2. `POST /pessoas`
3. `PUT /pessoas/{id}`
4. `DELETE /pessoas/{id}`
5. `GET /curso`
6. `POST /curso`

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

Endpoints de teste (branch 2):

1. `GET /api/pessoas`
2. `POST /api/pessoas`
3. `GET /api/curso`
4. `POST /api/curso`
5. `GET /api/professor`
6. `GET /api/disciplina`

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

Endpoints de teste (branch 3):

1. `GET /api/pessoas/busca?nome=Ana&page=0&size=10&sort=nome,asc`
2. `GET /api/curso/busca?nome=Engenharia&page=0&size=10`
3. `GET /api/professor/busca?nome=Joao&page=0&size=10`
4. `GET /api/disciplina/busca?nome=Calculo&page=0&size=10`
5. `GET /api/turma/busca?nome=Turma A&page=0&size=10`
6. `GET /api/matricula/busca?page=0&size=10`
7. `GET /api/avaliacao/busca?page=0&size=10`

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

Endpoints de teste (branch 4):

1. `POST /api/auth/login`
2. `POST /api/auth/register`
3. `GET /api/pessoas` (com token)
4. `POST /api/pessoas` (com token)
5. `GET /api/curso/busca?page=0&size=10` (com token)

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

Endpoints de teste (branch 5):

1. `POST /api/pessoas` (validar `nome`, `email`, `idade`)
2. `POST /api/professor` (validar campos obrigatorios)
3. `POST /api/disciplina` (validar `nome` e carga)
4. `POST /api/turma` (validar `nome` e ano)
5. `POST /api/avaliacao` (validar faixa de nota)
6. `PUT /api/pessoas/{id}` com payload invalido (esperado `400`)

## Branch 6 - Pessoa/Curso separados

Observacao tecnica:

- No fluxo real desta stack, essa etapa nao introduziu ganho isolado adicional.
- Separacao funcional de Pessoa/Curso ja estava contemplada nas etapas anteriores.

Endpoints de teste (branch 6):

1. Repetir os testes de CRUD de Pessoa e Curso da branch 5.
2. Repetir os testes de busca da branch 3 para Pessoa e Curso.

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

Endpoints de teste (branch 7):

1. `POST /api/auth/login` (professor)
2. `POST /api/auth/login` (aluno)
3. `POST /api/auth/register-aluno`
4. `GET /api/auth/me` (com token)
5. `GET /api/auth/usuarios` (somente professor)
6. `POST /api/pessoas` com token de aluno (esperado `403`)

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

Endpoints de teste (branch 8):

1. `GET http://localhost:8080/gateway/matriculas`
2. `GET http://localhost:8080/gateway/matriculas/{id}`
3. `POST http://localhost:8080/gateway/matriculas`
4. `GET http://localhost:8081/api/matriculas`
5. `GET http://localhost:8081/api/matriculas/{id}/detalhada`

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
