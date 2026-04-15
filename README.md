# API de Gerenciamento de Estoque e Pedidos (Suplementos)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%232496ED.svg?style=for-the-badge&logo=docker&logoColor=white)

API RESTful desenvolvida em Java com Spring Boot para o gerenciamento de uma loja de suplementos. O sistema atua como um OMS (Order Management System), permitindo o controle de estoque, registro de pedidos e notificações automatizadas.

## 🚀 Tecnologias e Arquitetura

O projeto utiliza padrões de arquitetura corporativa para garantir escalabilidade e manutenção:

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3
* **Banco de Dados:** PostgreSQL (Persistência relacional)
* **Containerização:** Docker & Docker Compose
* **Padrões Adotados:**
    * **Arquitetura em Camadas:** Isolamento entre Controller, Service e Repository.
    * **Padrão DTO:** Uso de `Records` para transferência de dados imutáveis.
    * **UUID:** Identificadores únicos para maior segurança dos recursos.
    * **Spring Security:** Proteção de rotas e infraestrutura para JWT.

## 📦 Roadmap do Projeto

- [x] **Sprint 1: Gestão de Estoque**
    - [x] Configuração de ambiente com Docker e PostgreSQL.
    - [x] Cadastro de Produtos com validação de campos.
    - [ ] Busca de produtos por nome e ID.
    - [ ] Exclusão lógica/física de itens.
- [ ] **Sprint 2: Sistema de Pedidos**
    - [ ] Processamento de vendas com baixa automática de estoque.
    - [ ] Garantia de consistência com `@Transactional`.
- [ ] **Sprint 3: Mensageria e UX**
    - [ ] Notificação de pedidos via E-mail (Async).
    - [ ] Tratamento global de erros padronizado.
