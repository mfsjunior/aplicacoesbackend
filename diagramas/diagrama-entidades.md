# Diagrama de Entidades - Dominio Academico

Visao consolidada das entidades do projeto monolitico e das relacoes de negocio.

```mermaid
erDiagram
    PESSOA {
        Long id
        String nome
        Integer idade
        String email
        Boolean ativo
    }

    CURSO {
        Long id
        String nome
        String descricao
        Integer cargaHoraria
        Boolean ativo
    }

    PROFESSOR {
        Long id
        String nome
        String especialidade
        String email
        Boolean ativo
    }

    DISCIPLINA {
        Long id
        String nome
        Integer cargaHoraria
        Boolean ativo
    }

    TURMA {
        Long id
        String nome
        Integer ano
        Boolean ativo
    }

    MATRICULA {
        Long id
        Long pessoaId
        Long cursoId
        String dataMatricula
        Boolean ativo
    }

    AVALIACAO {
        Long id
        Long pessoaId
        Long disciplinaId
        Double nota
        String data
        Boolean ativo
    }

    USUARIO {
        Long id
        String username
        String password
        String role
    }

    PESSOA ||--o{ MATRICULA : realiza
    CURSO ||--o{ MATRICULA : recebe
    PESSOA ||--o{ AVALIACAO : recebe
    DISCIPLINA ||--o{ AVALIACAO : avalia
    PROFESSOR ||--o{ DISCIPLINA : ministra
    TURMA ||--o{ PESSOA : agrupa
```

## Endpoints centrais para validacao funcional

1. `GET /api/pessoas`
2. `GET /api/curso`
3. `GET /api/professor`
4. `GET /api/disciplina`
5. `GET /api/turma`
6. `GET /api/matricula`
7. `GET /api/avaliacao`
8. `POST /api/auth/login`
