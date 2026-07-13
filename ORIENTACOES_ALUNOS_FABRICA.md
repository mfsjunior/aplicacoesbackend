# Orientacoes para Alunos da Fabrica

Este guia define como executar a atividade de forma correta, com evolucao por eventos (branches), sem pular etapas de aprendizado.

## Objetivo da atividade

Cada aluno deve implementar codigo manualmente, acompanhando a linha evolutiva do projeto da primeira ate a ultima branch.

## Ordem de evolucao que deve ser seguida

1. branch-1-monolito-basico
2. branch-2-alteracao-banco-dados
3. branch-3-consultas-avancadas
4. branch-4-auth-jwt
5. branch-5-entidades-avancadas
6. branch-6-pessoa-curso-separados (quando aplicavel)
7. branch-7-roles-professor-aluno
8. branch-8-microservicos

## Plano de execucao para duas entidades (modelo pratico)

Escolha duas entidades e aplique o ciclo abaixo do inicio ao fim.

### Etapa 1 - Monolito basico

Para cada uma das 2 entidades escolhidas:

1. Model
2. Repository
3. Service
4. Controller
5. DataLoader (quando houver no projeto)
6. CRUD funcionando

Validar no Postman:

- GET
- POST
- PUT
- DELETE

### Etapa 2 - Alteracao de banco

Para as mesmas 2 entidades:

1. Ajustar persistencia conforme a etapa (transicao de banco definida no projeto)
2. Confirmar funcionamento dos mesmos endpoints de CRUD

### Etapa 3 - Consultas avancadas

Para as mesmas 2 entidades:

1. Adicionar busca com filtros
2. Adicionar paginacao
3. Adicionar ordenacao
4. Criar/ajustar endpoint de busca

Validar retornos paginados e parametros de filtro.

### Etapa 4 - Auth JWT

1. Garantir login e emissao de token
2. Proteger rotas das 2 entidades com token
3. Testar acesso com e sem token

### Etapa 5 - Entidades avancadas

1. Adicionar validacoes de dominio nas 2 entidades
2. Usar validacao de entrada nos endpoints de escrita
3. Validar erro 400 para payload invalido

### Etapa 6 - Pessoa/Curso separados

1. Aplicar somente se houver alteracao real nesta etapa da sua trilha
2. Se nao houver ganho tecnico novo, documentar que foi absorvida pelas etapas anteriores

### Etapa 7 - Roles Professor/Aluno

1. Aplicar controle de acesso por perfil
2. Testar permissoes em endpoints das 2 entidades
3. Confirmar acesso de leitura e bloqueio de escrita conforme regra da etapa

### Etapa 8 - Microservicos e Gateway

Para chegar ao gateway de forma logica:

1. Separar as 2 entidades em servicos (quando couber no desenho da etapa)
2. Garantir endpoint de cada servico funcionando localmente
3. Configurar descoberta/enderecamento entre servicos conforme o projeto
4. Configurar rotas no gateway
5. Validar chamada via gateway para os endpoints das 2 entidades

## Como copiar da ultima branch sem perder o aprendizado

Se a branch final ja estiver completa:

1. Nao copiar o repositorio inteiro de uma vez.
2. Copiar por blocos de responsabilidade, na ordem:
- model
- repository
- service
- controller
- seguranca
- validacoes
- microservicos
- gateway
3. A cada bloco copiado, subir aplicacao e testar antes de avancar.
4. Registrar o que foi adaptado manualmente nas 2 entidades escolhidas.

## Critério minimo para aprovacao

1. Duas entidades completas atravessando toda a evolucao da branch 1 ate a branch 8.
2. Evidencia de testes de cada etapa.
3. Demonstacao de funcionamento no gateway ao final.
4. Explicacao tecnica do que mudou em cada evento aplicado.

## Entregaveis esperados

1. Link da branch de trabalho
2. Collection e Environment do Postman
3. Evidencias dos endpoints por etapa
4. Resumo tecnico por branch das 2 entidades escolhidas
