# Projeto GraphQL Java

Projeto público desenvolvido por **Marcio Garcia**  
[LinkedIn: Marcio de Almeida Garcia](https://www.linkedin.com/in/marcio-de-almeida-garcia-3a9ab6227/)

---

## Descrição

Este projeto demonstra como criar uma API GraphQL utilizando Java com Spring Boot.  
O objetivo é mostrar, na prática, a implementação de endpoints GraphQL para consulta e manipulação de dados, comparando com a arquitetura tradicional de APIs RESTful.

---

## Funcionalidades

- Exemplo completo de API GraphQL em Java
- Consultas (queries) e alterações (mutations) de dados
- Estrutura modular e clara para estudos e expansão
- Boas práticas para desenvolvimento com Spring Boot e GraphQL

---

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring for GraphQL
- Maven

---

## Como Executar Localmente

---

1. **Pré-requisitos**  

   - Java 21
   - Maven instalado

---

2. **Clonar o repositório**

   ```sh
   git clone (https://github.com/timarciogarcia/GraphQL.git)
   cd graphQL

---

3. **Executar**
   
   Melhor no Postman ou outro similar, necessita o envio de json/body para todas as operações
   http://localhost:8081/graphql

---

4. Estrutura

  - src/main/java/com/microinfor/graphQL/
    - controller
    - exception
    - model
    - service
  - src/main/resources/
    - application.properties
    - graphql/
        - schema.graphqls
        - (outros arquivos .graphqls)

---

5. O que é GraphQL? E qual a diferença para uma API RESTful?

     GraphQL é uma linguagem de consulta criada pelo Facebook que permite aos clientes (frontend, apps, etc.) solicitarem exatamente os dados de que precisam, sem excessos ou faltas.
     Ao contrário de APIs REST, que possuem múltiplos endpoints com respostas fixas, uma API GraphQL expõe geralmente um único endpoint, e o cliente define a estrutura da resposta por meio da query enviada.
     
     Principais diferenças entre REST e GraphQL
   
     | REST                                                 | GraphQL                                          |
     | ---------------------------------------------------- | ------------------------------------------------ |
     | Múltiplos endpoints (`/usuarios`, `/produtos`, etc.) | Normalmente um único endpoint (`/graphql`)       |
     | Backend define todos os campos retornados            | Cliente escolhe exatamente os campos da resposta |
     | Overfetching/Underfetching frequente                 | Só retorna o que foi solicitado                  |
     | Geralmente precisa versionar (`/v1/usuarios`)        | Menos necessidade de versionamento               |
     | Usa GET, POST, PUT, DELETE, etc.                     | Normalmente apenas POST                          |
   
     ## Exemplo prático
     ## REST:
     http
     GET /usuarios/1
     {
       "id": 1,
       "nome": "João",
       "email": "joao@email.com",
       "pets": [
         { "nome": "Rex", "tipo": "Cachorro" }
       ]
     }
     ## GraphQL:  
     graphql
     query {
       usuario(id: 1) {
         nome
         pets {
           nome
         }
       }
     }
   
     No GraphQL, você solicita apenas os campos que precisa, tornando a resposta mais enxuta e eficiente.
   
     ---
     
     ## Como consumir esta API GraphQL
   
     Depois que a aplicação estiver rodando, você pode acessar o Playground/GraphiQL pelo navegador para testar suas queries e mutations, ou usar ferramentas como Insomnia ou Postman.
     
     Exemplos de uso
   
     Query (consulta):
     
     graphql
   
     query {
       listarUsuarios {
         id
         nome
         email
       }
     }
     Mutation (alteração):
     
     graphql
   
     mutation {
       criarUsuario(nome: "Maria", email: "maria@email.com") {
         id
         nome
       }
     }
     
     Consulte o schema GraphQL pelo Playground para ver todas as queries, mutations e tipos disponíveis.
   
     ---
     
     ## Observações
   
     Este projeto é didático, mas pode ser facilmente expandido para aplicações reais.
     O schema GraphQL pode ser dividido em vários arquivos .graphqls e organizado em subpastas dentro de /resources/graphql para facilitar a manutenção.
     As exceções da API são tratadas e estruturadas de forma amigável, facilitando o diagnóstico de erros.


     # Análise 

      ## 1. REST API
         
      Passos de uma requisição REST típica:
   
      Cliente faz requisição HTTP (ex: GET /usuarios/1)
      
      Servidor roteia a requisição para o endpoint correspondente (ex: /usuarios/{id})
      
      Controller/Handler REST é chamado
      
      Validação de parâmetros (automática ou manual)
      
      Chamada do Service responsável pelo domínio
      
      Consulta ao banco de dados (ou cache, se existir)
      
      Service retorna objeto(s) para o controller
      
      Controller serializa o objeto em JSON
      
      Resposta HTTP enviada ao cliente com todos os dados definidos pelo endpoint
      
      Vantagens de velocidade:
      
      Se o endpoint é bem definido e retorna só o que o cliente precisa, o fluxo é rápido e simples.
      
      Pode ser altamente otimizado (caching, load balancer, compactação etc.).
      
      Desvantagens:
      
      Se o cliente precisa de muita flexibilidade (ou dados de vários recursos), pode exigir múltiplas requisições (N+1 problem), aumentando a latência.
      
      Pode retornar dados desnecessários (“overfetching”) ou faltar dados (“underfetching”), forçando novas chamadas.
      
      ## 2. GraphQL API
   
      Passos de uma requisição GraphQL típica:
   
      Cliente faz uma requisição HTTP POST ao endpoint /graphql, enviando uma query no corpo da requisição (ex: “quero nome e pets do usuário X”).
      
      Servidor recebe a query GraphQL e repassa para o GraphQL engine.
      
      Parser e validação da query (checa sintaxe, tipos e permissões).
      
      Resolver principal é chamado conforme a root query (ex: usuario(id: 1)).
      
      Cada campo solicitado ativa um “resolver” responsável por buscar a informação.
      
      Se a query for aninhada, cada campo pode disparar uma nova função de busca.
      
      Consultas ao banco de dados (possivelmente otimizadas, mas se mal feito pode gerar múltiplas queries).
      
      GraphQL engine monta o JSON de resposta, exatamente com os campos solicitados.
      
      Resposta HTTP enviada ao cliente.
      
      Vantagens de velocidade:
      
      Para o cliente, normalmente basta uma única requisição para obter exatamente o que precisa, mesmo para dados aninhados.
      
      Evita “overfetching” (não traz campos desnecessários) e “underfetching” (sempre pode pedir mais um campo na mesma consulta).
      
      Desvantagens:
      
      A montagem da resposta pode ser mais complexa no backend e, se os resolvers não forem otimizados, pode ocorrer o N+1 problem dentro do servidor (vários acessos ao banco).
      
      Validação e montagem do JSON podem ter uma leve sobrecarga em queries complexas.
      
      Mais difícil de aplicar caching em nível de HTTP, pois toda requisição vai para o mesmo endpoint.
      
      ## Comparativo de Velocidade
   
         | Cenário                      | REST API                            | GraphQL API                        |
         | ---------------------------- | ----------------------------------- | ---------------------------------- |
         | Buscar 1 recurso simples     | Muito rápido                        | Muito rápido                       |
         | Buscar recursos aninhados    | Pode precisar de múltiplas chamadas | Uma única chamada (mais eficiente) |
         | Dados em excesso ou em falta | Retorna tudo que o endpoint define  | Retorna só o que o cliente pediu   |
         | Requisição HTTP por recurso  | 1 chamada por endpoint              | 1 chamada para tudo                |
         | Otimização/caching           | Muito fácil (por URL)               | Mais complexo (por query)          |
         | Processamento no servidor    | Simples                             | Pode ser mais complexo             |
      
      ## Em geral:
      
      REST é rápido e simples para operações diretas e bem definidas, e fácil de cachear, mas pode exigir múltiplas requisições para dados relacionados.
      
      GraphQL é eficiente para interfaces ricas (frontends modernos) porque minimiza o número de requisições e traz exatamente o que o cliente precisa, mas pode exigir cuidado com otimização do backend para evitar múltiplas consultas desnecessárias ao banco.
      
      Resumo Didático
      Para APIs simples ou uso interno com endpoints bem definidos, REST tende a ser mais rápido e direto.
      
      Para APIs públicas, flexíveis ou com frontends modernos (React, Angular, apps mobile), GraphQL oferece uma experiência de usuário mais fluida e com menos “ida e volta” ao servidor.
      
      Ambos podem ser muito rápidos se bem implementados, mas GraphQL dá ao cliente mais poder para definir a resposta, enquanto REST é mais simples e previsível para manutenção e otimização clássica
