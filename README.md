# Desafio MV - Sistema de Cadastro de Colaboradores e Itens de Café

## **Descrição**

Este projeto é uma aplicação completa que gerencia colaboradores e itens de café em uma empresa.  
Ele possui backend em **Spring Boot**, frontend em **Angular**, integração via **REST API**, testes unitários e de integração com **JUnit 5**, documentação com **Swagger** e suporte a execução via **Docker**.

O sistema permite:

- Cadastro de colaboradores
- Cadastro de itens de café (com validação de datas)
- Listagem de colaboradores e itens
- Controle de status dos itens de café (Pendente, Concluído)
- Frontend amigável em Angular
- Documentação da API via Swagger

---

## **Tecnologias Utilizadas**

- **Backend:** Java 17, Spring Boot, Spring Data JPA, Hibernate, H2/MySQL
- **Frontend:** Angular, Angular Material, SCSS
- **Testes:** JUnit 5, Mockito
- **Documentação:** Swagger (OpenAPI)
- **Containerização:** Docker, Docker Compose

---

## **Pré-requisitos**

- Java 17+
- Maven 3+
- Node.js 18+ (para rodar Angular localmente)
- NPM 8+
- Docker e Docker Compose (opcional)

---

## **Executando o projeto localmente**

Rodando via Docker Compose

Certifique-se que Docker e Docker Compose estão instalados.

Na raiz do projeto (onde está o docker-compose.yml):

docker-compose up --build


Isso irá buildar e rodar backend e frontend juntos, expondo:

Backend: http://localhost:8081
Frontend: http://localhost:4200


Funcionalidades:

- Cadastro de Colaboradores: Nome, CPF

- Cadastro de Itens de Café: Nome do item, data, colaborador responsável, status

Validações:

- Data do café deve ser após a data atual

- Não permite itens duplicados para o mesmo dia
  

Lista de Participantes: Exibe colaboradores e itens cadastrados

Atualização de Status: Permite atualizar status de itens (Pendente, Concluído)

Testes automatizados: Unitários com JUnit 5

Contato

Nome: Clara Santos

E-mail: clarabsantos02@gmail.com

GitHub: https://github.com/Clara-Santos02
