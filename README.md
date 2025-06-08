# Cadastro de Pessoas

Este é um projeto Spring Boot para gerenciamento de cadastro de pessoas com autenticação JWT. O sistema implementa
segurança com Spring Security, persistência com JPA, migrações com Liquibase e autenticação baseada em tokens.

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.5.0
- Spring Security
- Spring Data JPA
- PostgreSQL
- Liquibase
- JWT (com Auth0)
- Lombok

## Configuração

### Banco de Dados

O projeto utiliza PostgreSQL. As configurações estão em `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/cadastroPessoas
    username: postgres
    password: postgres
```

Crie um banco com o nome `cadastroPessoas` antes de rodar a aplicação.

### JWT

A chave do token JWT pode ser definida via variável de ambiente:

```
export JWT_SECRET="sua_chave_secreta"
```

Ou será usado o valor padrão `my_secret_key`.

## Endpoints

### Autenticação

- `POST /auth/login` – Login de usuário com email e senha.
- `POST /auth/register` – Registro de novo usuário.

### Pessoa

Requer autenticação JWT.

- `GET /api/pessoas` – Lista todas as pessoas.
- `GET /api/pessoas/{id}` – Retorna uma pessoa por ID.
- `PUT /api/pessoas/{id}` – Atualiza uma pessoa.
- `DELETE /api/pessoas/{id}` – Remove uma pessoa.

## Executando

```bash
./mvnw spring-boot:run
```

## Testes

Execute os testes com:

```bash
./mvnw test
```

## Estrutura do Projeto

- `domain/` – Entidades do sistema.
- `controller/` – Controllers REST.
- `service/` – Lógica de negócios.
- `security/` – Lógica de autenticação JWT.
- `dto/` – Objetos de transferência de dados.