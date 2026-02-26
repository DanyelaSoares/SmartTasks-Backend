# 🚀 SmartTasks – Backend

Backend da aplicação **SmartTasks**, um sistema fullstack de gerenciamento de tarefas desenvolvido com foco em arquitetura, segurança e boas práticas.

---

# 📌 1. Visão Geral

O **SmartTasks** é o backend de uma aplicação fullstack para gerenciamento de tarefas.

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

---

# 🎯 2. Objetivos do Projeto

- Implementar autenticação stateless com JWT
- Aplicar Spring Security com filtro personalizado
- Estruturar backend em camadas
- Garantir isolamento de tarefas por usuário
- Preparar base para evolução (roles, testes, Swagger)

---

# 🏗 3. Arquitetura

O projeto segue arquitetura em camadas:

Controller → Exposição de endpoints REST
Service → Regras de negócio
Repository → Persistência de dados
Security → JWT + Filtro de autenticação
Config → CORS + SecurityFilterChain
Model → Entidades JPA

## 🔎 Estrutura de Pacotes
```
com.smarttasks
├── config
├── controller
├── model
├── repository
├── security
└── service
```

---

# 🔐 4. Autenticação e Segurança

A autenticação é baseada em **JWT (JSON Web Token)**.

## 🔄 Fluxo de Login
```
1. Usuário envia email e senha para `/auth/login`
2. Backend valida credenciais no banco
3. Backend gera token JWT
4. Token é retornado ao frontend
5. Frontend envia token nas requisições protegidas:

Authorization: Bearer <token>

6. `JwtAuthFilter` intercepta e valida o token
```
---
# 📡 5. Endpoints

## 🔓 Autenticação

### POST `/auth/register`

Cadastra novo usuário.

```json
{
  "name": "Daniela",
  "email": "daniela@teste.com",
  "senha": "123456"
}
```
POST /auth/login

Realiza login e retorna token JWT.
```
{
"email": "daniela@teste.com",
"senha": "123456"
}
```
Resposta:
```
{
"email": "daniela@teste.com",
"token": "eyJhbGciOiJIUzI1NiJ9..."
}
```
🔒 Tarefas (JWT obrigatório)
GET /tasks

Lista tarefas do usuário autenticado

---
POST /tasks

Cria nova tarefa.
```
{
  "titulo": "Estudar Spring Security"
}
```
# 🗄 6. Modelo de Dados
📌 Entidade User
```
Campo	Tipo	Descrição
id	Long	Identificador
name	String	Nome do usuário
email	String	Email único
senha	String	Senha
```
📌 Entidade Task
```
Campo	Tipo	Descrição
id	Long	Identificador
titulo	String	Título da tarefa
concluida	boolean	Status da tarefa
user	User	Relacionamento N:1
```
🔗 Relacionamento
```
User 1:N Task
Task N:1 User
```
# 🧩 7. UML
📌 7.1 Diagrama de Casos de Uso

[Veja aqui](img/uml/caso-de-uso.svg)

📌 7.2 Diagrama de Classes

[Veja aqui](img/uml/classes.svg)

📌 7.3 Diagrama de Sequência – Login

[Veja aqui](img/uml/sequencia.svg)

# ⚙️ 8. Configuração e Execução
8.1 Banco de Dados
```
CREATE DATABASE smarttasks;
```
8.2 application.properties
```
spring.datasource.url=jdbc:mysql://localhost:3306/smarttasks
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
8.3 Executar
````dtd
mvn clean install
mvn spring-boot:run
````
Servidor disponível em:
````dtd
http://localhost:8080
````
# 🚀 9. Próximas Evoluções

🔐 Criptografia de senha com BCrypt

👥 Implementação de roles (ADMIN / USER)

✏ Atualização e exclusão de tarefas

🧪 Testes unitários

📘 Swagger/OpenAPI

# ‍💻 10. Autora

Daniela Soares

Projeto desenvolvido para portfólio fullstack.