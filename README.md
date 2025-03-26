# Call for Papers API

## Descrição

Essa é uma aplicação que gerencia um Call for Papers (CFP) onde usuários podem submeter e
visualizar propostas de palestras.

## Tecnologias usadas

Para o desenvolvimento dessa API, foi utilizado:
- `Java`: Na versão JDK 21;
- `Maven`: Build do projeto;
- `Spring Security`: Segurança da API;
- `Spring Data JPA + Hibernate`: Gerenciamento da camada de dados;
- `PostgreSQL`: Banco de dados relacional do projeto;
- `Banco de dados H2`: Banco de dados para os testes do projeto;
- `Docker`: Para disponibilizar uma imagem de banco de dados PostgreSQL;
- `Postman`: Testes da API;
- `Git e GitHub`: Versionamento da aplicação;
- `GitHub Actions`: Gerenciamento do CI do projeto;
- `BeeKeeper Studio`: Interação com banco de dados.

## Instalação do projeto

Para utilizar este projeto na sua máquina local, é necessário:
- `Java JDK 21`: Versão do Java em que a API foi construída;
- `Maven`: Para gerenciar a build do projeto;
- `Docker`: Para gerenciar a imagem do banco de dados PostgreSQL;
- `IDE com suporte ao Lombock`: Necessário para a geração de código boilerplate. 

Após clonar o projeto, no terminal de comandos da sua IDE execute o comando `mvn install`, o qual instalará a aplicação em seu computador.
Após terminar a instalação, execute o projeto normalmente, o qual disponibilizará a API na porta 8080 do localhost do seu computador.

Para facilitar os testes da aplicação, foi disponibilizado no repositório o arquivo `callforpapers.postman_collection.json`, o qual ao ser importado no Postman fornece uma configuração rápida para fazer a testagem dos endpoints da API.

## URLs da API

A API contém três URLs padrão:

[`http://localhost:8080/users`](http://localhost:8080/users) → Gerencia a criação e listagem de usuários

[`http://localhost:8080/login`](http://localhost:8080/login) → Gerencia a autenticação da API

[`http://localhost:8080/talk-proposal`](http://localhost:8080/talk-proposal) → Gerencia a lógica central da API

## Endpoints

### `GET /users`

Retorna a lista de usuários cadastrados no sistema.

#### Códigos HTTP:

Retorna o código de status 200 em caso de sucesso, e o status 401 caso não tenha sido possível encontrar os usuários devido a falta de autorização. 

#### Resposta:

Retorna um JSON com as seguintes propriedades:

- `userId`: Id do usuário;
- `username`: Nome de usuário;
- `password`: Senha do usuário;
- `roleId`: Informações referentes ao cargo do usuário:
    - `roleId`: Identificador do cargo do usuário;
    - `roleName`: Nome do cargo do usuário.

#### Exemplo:

Request:

```
GET /users
```

Response:

```json
[
    {
        "userId": "5039e643-dc54-4920-b9b1-20aa87bbe41d",
        "username": "nome",
        "password": "senha",
        "roles": [
            {
                "roleId": 1,
                "roleName": "admin"
            }
        ]
    },
    {
        "userId": "07946155-1daf-4a1a-9190-77a20d69ec8c",
        "username": "nome",
        "password": "senha",
        "roles": [
            {
                "roleId": 2,
                "roleName": "basic"
            }
        ]
    }
]

```

### **POST /users**

Cria um novo usuário no sistema.

#### **Resposta:**

Retorna um JSON com as seguintes propriedades:

- `username`: Username do usuário;
- `password`: Senha do usuário;

#### Códigos HTTP:

Retorna o código de status 201 caso o usuário tenha sido criado com sucesso, e o status 401 caso não tenha sido possível cadastrar o usuário.

#### **Exemplo:**

Request:

POST /users

Response:

```
{
    "username": "login",
    "password": "senha"
}
```

### **POST /login**

Faz login no sistema.

#### **Resposta:**

Retorna um JSON com as seguintes propriedades:

- `accessToken`: Token JWT do usuário logado;
- `expiresIn`: Tempo de expiração do token JWT.

#### Códigos HTTP:

Retorna o código de status 200 o login tenha ocorrido com sucesso, e o status 401 caso não tenha sido possível encontrar o usuário ou as credenciais não estejam corretas.

#### **Exemplo:**

Request:

POST /users

Response:

```json
{
    "accessToken": "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJDYWxsRm9yUGFwZXJzIiwic3ViIjoiNTAzOWU2NDMtZGM1NC00OTIwLWI5YjEtMjBhYTg3YmJlNDFkIiwiZXhwIjoxNzQyOTQ5NzE4LCJpYXQiOjE3NDI5NDk0MTgsInNjb3BlIjoiYWRtaW4ifQ.zk6T5s6kqqe2Rrp8WCZl-rEN6gejDsNAI6-ABwbXTrpIktkZi4MXHjc0uqjvbLLXxZ_cLc-pjxJUJqTiP_hdN6w4H4Gvr5wjJbBcExao692CdDPV1KVJr7-eijN6qxK0Dywz2TGAyF_tJUJGUlVuumjy8YUgWDR3bwShksFa7X0Un5q7dVMx9Z_8q5KovLxB2uq3m5OPPEyxg7q3M560FOsW6vDXkHXz3JDuJHR4q4leUoCD-ZmyULJPKydqDBuuSbA4zlsUWu3lV3gZZbykxKktlJlKO4HwIfkFU4yPfmBTRcmLROGAi90Yt7-UJsHprJyj9SIT-k7H5vyLZ251nA",
    "expiresIn": 300
}
```

### **POST /talk-proposal**

Cadastra uma nova palestra no sistema.

#### **Resposta:**

Retorna um JSON com as seguintes propriedades:

- `title`: Título da palestra cadastrada;
- `resume`: Resumo da palestra cadastrada.
- `authorName`: Nome do autor da palestra cadastrada.
- `authorEmail`: E-mail do autor da palestra cadastrada.

#### Códigos HTTP:

Retorna o código de status 200 caso a palestra tenha sido cadastrada com sucesso, e o status 422 caso a palestra já se encontre no banco de dados ou não tenha sido especificada corretamente.

#### **Exemplo:**

Request:

POST /**talk-proposal**

Response:

```json
{
    "title": "titulo",
    "resume": "resumo",
    "authorName": "nome",
    "authorEmail": "enderecod@email.com"
}
```

### **DELETE /talk-proposal{id}**

Deleta uma palestra cadastrada no sistema, especificada pelo ID passado no endereço da requisição.

#### **Resposta:**

Retorno sem corpo.

#### Códigos HTTP:

Retorna o código de status 204 caso tenha sido encontrada a palestra e ela tenha sido deletada com sucesso, e o status 404 caso não tenha sido possível encontrar a palestra.

### **PUT /talk-proposal/{id}**

Atualiza uma palestra já cadastrada no sistema, especificada pelo ID passado no endereço da requisição.

#### **Resposta:**

Retorna um JSON com as seguintes propriedades:

- `title`: Título da palestra cadastrada;
- `resume`: Resumo da palestra cadastrada.
- `authorName`: Nome do autor da palestra cadastrada.
- `authorEmail`: E-mail do autor da palestra cadastrada.

Estes dados serão atualizados conforme o corpo de requisição do endpoint, retornando tanto os campos atualizados como os que foram mantidos iguais.

#### Códigos HTTP:

Retorna o código de status 200 caso tenha sido encontrada a palestra e ela tenha sido atualizada, e o status 404 caso não tenha sido possível encontrar a palestra.

#### **Exemplo:**

Request:

PUT /**talk-proposal{id}**

Response:

```json
{
    "title": "titulo atualizado",
    "resume": "resumo",
    "authorName": "nome",
    "authorEmail": "email-atualizado@email.com"
}
```

### **GET /talk-proposal/{id}**

Busca uma palestra já cadastrada no sistema, especificada pelo ID passado no endereço da requisição.

#### **Resposta:**

Retorna um JSON com as seguintes propriedades:

- `title`: Título da palestra cadastrada;
- `resume`: Resumo da palestra cadastrada.
- `authorName`: Nome do autor da palestra cadastrada.
- `authorEmail`: E-mail do autor da palestra cadastrada.

#### Códigos HTTP:

Retorna o código de status 200 caso tenha sido encontrada a palestra, e o status 404 caso não tenha sido possível encontrar a palestra.

#### **Exemplo:**

Request:

GET /**talk-proposal{id}**

Response:

```json
{
    "title": "titulo",
    "resume": "resumo",
    "authorName": "nome",
    "authorEmail": "endereco@email.com"
}
```

### **GET /talk-proposal**

Busca todas as palestras já cadastradas no sistema.

#### **Resposta:**

Retorna um JSON com as seguintes propriedades:

- `title`: Título da palestra cadastrada;
- `resume`: Resumo da palestra cadastrada.
- `authorName`: Nome do autor da palestra cadastrada.
- `authorEmail`: E-mail do autor da palestra cadastrada.

#### Códigos HTTP:

Retorna o código de status 200 em caso de sucesso, e o status 401 caso não tenha sido possível acessar as palestras devido a falta de autorização.

#### **Exemplo:**

Request:

GET /**talk-proposal**

Response:

```json
[
    {
        "title": "titulo A",
        "resume": "resumo A",
        "authorName": "nome A",
        "authorEmail": "endereco-A@email.com"
    },
    {
        "title": "titulo B",
        "resume": "resumo B",
        "authorName": "nome B",
        "authorEmail": "endereco-B@email.com"
    },
    {
        "title": "titulo C",
        "resume": "resumo C",
        "authorName": "nome C",
        "authorEmail": "endereco-C@email.com"
    },
    {
        "title": "titulo D",
        "resume": "resumo D",
        "authorName": "nome D",
        "authorEmail": "endereco-D@email.com"
    }
]
```
