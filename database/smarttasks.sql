-- =====================================================
-- SmartTasks Database Script
-- =====================================================

CREATE DATABASE IF NOT EXISTS smarttasks
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE smarttasks;

-- =====================================================
-- Tabela de usuários
-- =====================================================

CREATE TABLE users (
id BIGINT NOT NULL AUTO_INCREMENT,
email VARCHAR(255) NOT NULL,
nome VARCHAR(255) NOT NULL,
senha VARCHAR(255) NOT NULL,
PRIMARY KEY (id)
);

-- =====================================================
-- Tabela de tarefas
-- =====================================================

CREATE TABLE tasks (
id BIGINT NOT NULL AUTO_INCREMENT,
titulo VARCHAR(255) DEFAULT NULL,
description TEXT,
priority VARCHAR(20) DEFAULT NULL,
due_date DATE DEFAULT NULL,
concluida BIT(1) NOT NULL DEFAULT b'0',
user_id BIGINT NOT NULL,

```
PRIMARY KEY (id),

KEY fk_user (user_id),

CONSTRAINT fk_user
    FOREIGN KEY (user_id)
    REFERENCES users(id)
    ON DELETE CASCADE
```

);

-- =====================================================
-- Dados de exemplo (opcional)
-- =====================================================

INSERT INTO users (nome, email, senha)
VALUES (
'Usuário Teste',
'[teste@smarttasks.com](mailto:teste@smarttasks.com)',
'123456'
);
