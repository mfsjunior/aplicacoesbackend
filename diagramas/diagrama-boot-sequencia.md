# Diagrama de Sequencia - Boot da Aplicacao

Fluxo de inicializacao do backend com observacao da mudanca de persistencia entre as branches.

```mermaid
sequenceDiagram
    autonumber
    participant JVM as JVM
    participant Main as CrudMongoApplication.main()
    participant Spring as SpringApplication
    participant Context as ApplicationContext
    participant AutoConf as AutoConfiguration
    participant Security as SecurityConfig
    participant JWT as JwtAuthFilter
    participant Seed as DataLoaders
    participant DB as Banco

    JVM->>Main: inicia aplicacao
    Main->>Spring: SpringApplication.run(...)

    Spring->>Context: cria contexto IoC
    Context->>AutoConf: aplica configuracoes automaticas

    Note over AutoConf,DB: Branch 1: MongoDB (Atlas/local)
    Note over AutoConf,DB: Branch 2+: JPA com banco relacional

    Context->>Security: cria SecurityFilterChain
    Security->>JWT: registra filtro JWT

    Context->>Seed: executa CommandLineRunner
    Seed->>DB: popula dados iniciais

    Context-->>Spring: contexto pronto
    Spring-->>Main: aplicacao disponivel na porta 8080
```

## Pontos de verificacao

1. Inicializacao sem erro de conexao com banco.
2. Endpoints de autenticacao respondendo em `/api/auth/*`.
3. Endpoints de dominio respondendo com token valido.
