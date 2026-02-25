# SmartTasks – Backend
![Status](https://img.shields.io/badge/Status-Em%20desenvolvimento-yellow?style=for-the-badge)


Este repositório contém o **backend** do projeto **SmartTasks**, um sistema de gerenciamento de tarefas fullstack.
O backend foi desenvolvido em **Java com Spring Boot** e fornece APIs REST consumidas pelo frontend em React.

---

## 🚀 Funcionalidades

- Gerenciamento de usuários
- Autenticação e autorização com Spring Security
- Endpoints REST para integração com o frontend
- Base para gerenciamento de tarefas (em evolução)

---

## 🛠 Tecnologias

- Java 17
- Spring Boot
- Spring Security
- PostgreSQL
- Maven

---

## 🔗 Frontend

O frontend do projeto está neste repositório separado:  
👉 https://github.com/DanyelaSoares/SmartTasks-Frontend

---

## ⚡ Como rodar

1. Configure o banco de dados PostgreSQL.
2. Atualize o arquivo `application.properties` com suas credenciais.
3. Execute no terminal:

```bash
mvn clean install
mvn spring-boot:run
```
O backend estará disponível em:
👉 http://localhost:8080

## 🧩 Arquitetura

O backend segue arquitetura baseada em camadas típica de aplicações Spring Boot:

- Controllers – exposição dos endpoints REST
- Services – regras de negócio
- Repositories – acesso a dados
