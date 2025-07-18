# Projeto GraphQL Java Example

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

1. **Pré-requisitos**  
   - Java 21
   - Maven instalado

2. **Clonar o repositório**
   ```sh
   git clone <URL-DO-SEU-REPOSITORIO>
   cd graphQL

---

3. **Executar**

   http://localhost:8080/graphiql

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

