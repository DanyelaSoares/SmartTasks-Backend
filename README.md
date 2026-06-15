
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![JWT](https://img.shields.io/badge/Auth-JWT-blue)
![MySQL](https://img.shields.io/badge/Database-MySQL-lightgrey)

# 🚀 SmartTasks API
Backend REST para gerenciamento de tarefas com autenticação JWT

---

## 📑 Sumário

- [Visão Geral](#-1-visão-geral)
- [Requisitos Funcionais](#-2-requisitos-funcionais)
- [Requisitos Não Funcionais](#-3-requisitos-não-funcionais)
- [Tecnologias Utilizadas](#-4-tecnologias-utilizadas)
- [Status do Projeto](#-5-status-do-projeto)
- [Objetivos do Projeto](#-6-objetivos-do-projeto)
- [Arquitetura em Camadas](#-7-arquitetura-em-camadas)
- [Autenticação e Segurança](#-8-autenticação-e-segurança)
- [Endpoints](#-9-endpoints)
- [Modelo de Dados](#-10-modelo-de-dados)
- [UML](#-11-uml)
- [Configuração e Execução](#-12-configuração-e-execução)
- [Próximas Evoluções](#-13-próximas-evoluções)
- [Autora](#-14-autora)

## 📌 1. Visão Geral

O **SmartTasks** é o backend de uma aplicação fullstack para gerenciamento de tarefas.
Este repositório contempla exclusivamente o backend da aplicação.

O sistema permite:

- ✅ Cadastro de usuários
- 🔐 Autenticação via JWT
- 📝 Criação de tarefas
- 📋 Listagem de tarefas por usuário autenticado
- ✔ Marcação de tarefas como concluídas

A aplicação foi desenvolvida com foco em:

- Organização arquitetural
- Separação de responsabilidades
- Segurança de rotas
- Estrutura escalável

## 📋 2. Requisitos Funcionais

RF01 – O sistema deve permitir cadastro de usuários.  
RF02 – O sistema deve permitir autenticação via JWT.  
RF03 – O sistema deve permitir criação de tarefas.  
RF04 – O sistema deve listar tarefas por usuário autenticado.  
RF05 – O sistema deve permitir marcar tarefas como concluídas.

## ⚙️ 3. Requisitos Não Funcionais

RNF01 – A autenticação deve ser stateless.  
RNF02 – As rotas protegidas devem exigir token válido.  
RNF03 – O sistema deve garantir isolamento de tarefas por usuário.

## 🛠 4. Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Security
- JWT
- JPA / Hibernate
- MySQL
- Maven

## 📌 5. Status do Projeto

- [x] Cadastro de usuário
- [x] Login com JWT
- [x] Filtro de autenticação
- [x] Criação de tarefas
- [x] Listagem por usuário
- [ ] Criptografia de senha com BCrypt
- [ ] Roles (ADMIN/USER)
- [ ] Swagger/OpenAPI

## 🎯 6. Objetivos do Projeto

- Implementar autenticação stateless com JWT
- Aplicar Spring Security com filtro personalizado
- Estruturar backend em camadas
- Garantir isolamento de tarefas por usuário
- Preparar base para evolução (roles, testes, Swagger)

---

## 🏗 7. Arquitetura em Camadas

O projeto segue arquitetura em camadas:

- **Controller** → Exposição de endpoints REST
- **Service** → Regras de negócio
- **Repository** → Persistência de dados
- **Security** → JWT + Filtro de autenticação
- **Config** → CORS + SecurityFilterChain
- **Model** → Entidades JPA

## 🔎 Estrutura de Pacotes

```text
com.smarttasks
├── config
├── controller
├── model
├── repository
├── security
└── service
```

---

## 🔐 8. Autenticação e Segurança

A autenticação é baseada em **JWT (JSON Web Token)**.

## 🔄 Fluxo de Login

1. Usuário envia email e senha para `/auth/login`
2. Backend valida credenciais no banco
3. Backend gera token JWT
4. Token é retornado ao frontend
5. Frontend envia o token nas requisições protegidas:  
   `Authorization: Bearer <token>`
6. `JwtAuthFilter` intercepta e valida o token

## 📡 9. Endpoints

### 🔓 Autenticação

### POST `/auth/register`

Cadastra novo usuário.

```json
{
  "name": "Daniela",
  "email": "daniela@teste.com",
  "senha": "123456"
}
```

### POST `/auth/login`

Realiza login e retorna token JWT.

```json
{
  "email": "daniela@teste.com",
  "senha": "123456"
}
```

Resposta:

```json
{
  "email": "daniela@teste.com",
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

## 🔒 Tarefas (JWT obrigatório)

### GET `/tasks`

Lista tarefas do usuário autenticado.

### POST `/tasks`

Cria nova tarefa.

```json
{
  "titulo": "Estudar Spring Security"
}
```

## 🗄 10. Modelo de Dados

## 📌 Entidade User

| Campo | Tipo   | Descrição           |
| ----- | ------ | ------------------- |
| id    | Long   | Identificador único |
| name  | String | Nome do usuário     |
| email | String | Email único         |
| senha | String | Senha do usuário    |

## 📌 Entidade Task

| Campo     | Tipo    | Descrição                        |
| --------- | ------- | -------------------------------- |
| id        | Long    | Identificador único              |
| titulo    | String  | Título da tarefa                 |
| concluida | boolean | Indica se a tarefa foi concluída |
| user      | User    | Relacionamento N:1 com User      |

🔗 Relacionamento

```text
User 1:N Task
Task N:1 User
```

## 🧩 11. UML

📌 11.1 Diagrama de Casos de Uso  
[Veja aqui](img/uml/caso-de-uso.svg)

📌 11.2 Diagrama de Classes  
[Veja aqui](img/uml/classes.svg)

📌 11.3 Diagrama de Sequência – Login  
[Veja aqui](img/uml/sequencia.svg)

## ⚙️ 12. Configuração e Execução

### 12.1 Banco de Dados

```sql
CREATE DATABASE smarttasks;
```

### 12.2 application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/smarttasks
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 12.3 Executar

```bash
mvn clean install
mvn spring-boot:run
```

Servidor disponível em:

```text
http://localhost:8080
```

## 🚀 13. Próximas Evoluções

- 🔐 Criptografia de senha com BCrypt
- 👥 Implementação de roles (ADMIN / USER)
- ✏ Atualização e exclusão de tarefas
- 🧪 Testes unitários
- 📘 Swagger/OpenAPI

## 👩‍💻 14. Autora

**Daniela Soares**
